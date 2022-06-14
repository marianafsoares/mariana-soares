package com.whatsappgroup.whatsappgroupmessages.services.impl;

import com.whatsappgroup.whatsappgroupmessages.controllers.exception.contact.ContactNotExistException;
import com.whatsappgroup.whatsappgroupmessages.controllers.exception.group.GroupNotExistException;
import com.whatsappgroup.whatsappgroupmessages.controllers.exception.message.MessageNotExistException;
import com.whatsappgroup.whatsappgroupmessages.controllers.exception.message.MessagesByContactAndGroupNotExistException;
import com.whatsappgroup.whatsappgroupmessages.controllers.exception.message.MessagesByGroupNotExistException;
import com.whatsappgroup.whatsappgroupmessages.dtos.MessageDTO;
import com.whatsappgroup.whatsappgroupmessages.mappers.MessageMapper;
import com.whatsappgroup.whatsappgroupmessages.models.Contact;
import com.whatsappgroup.whatsappgroupmessages.models.Group;
import com.whatsappgroup.whatsappgroupmessages.models.Message;
import com.whatsappgroup.whatsappgroupmessages.models.Notification;
import com.whatsappgroup.whatsappgroupmessages.repositories.IContactRepository;
import com.whatsappgroup.whatsappgroupmessages.repositories.IGroupRepository;
import com.whatsappgroup.whatsappgroupmessages.repositories.IMessageRepository;
import com.whatsappgroup.whatsappgroupmessages.repositories.INotificationRepository;
import com.whatsappgroup.whatsappgroupmessages.services.IMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageServiceImpl implements IMessageService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IMessageRepository messageRepository;

    @Autowired
    private INotificationRepository notificationRepository;

    @Autowired
    private IGroupRepository groupRepository;

    @Autowired
    private IContactRepository contactRepository;

    @Override
    public MessageDTO findByMessageId(long message_id) {
        logger.info("MessageService - findByMessageId - Begin; {}", message_id);
        Optional<Message> message = messageRepository.findById(message_id);

        message.orElseThrow(MessageNotExistException::new);

        logger.info("MessageService - findByMessageId - End; ");
        return MessageMapper.INSTANCE.messageToMessageDto(message.get());
    }

    @Override
    public Set<MessageDTO> findByContactAndGroup(long contactId, long groupId) {
        logger.info("MessageService - findByContactAndGroup - Begin; {}", contactId, groupId);

        Optional<Group> group = groupRepository.findById(groupId);
        group.orElseThrow(GroupNotExistException::new);

        Optional<Contact> contact = contactRepository.findById(contactId);
        contact.orElseThrow(ContactNotExistException::new);


        Optional<List<Message>> messages = messageRepository.findAllByContactAndGroup(contact.get(), group.get());
        messages.orElseThrow(MessagesByContactAndGroupNotExistException::new);

        logger.info("MessageService - findByContactAndGroup - End; ");
        Set<MessageDTO> messageDTOSet = new HashSet<>();

        for (Message message: messages.get()){
            messageDTOSet.add(MessageMapper.INSTANCE.messageToMessageDto(message));
        }
        return messageDTOSet;
    }

    @Override
    public Set<MessageDTO> findByGroup(long groupId) {
        logger.info("MessageService - findByGroup - Begin; {}", groupId);

        Optional<Group> group = groupRepository.findById(groupId);
        group.orElseThrow(GroupNotExistException::new);

        Optional<List<Message>> messages = messageRepository.findAllByGroup(group.get());

        messages.orElseThrow(MessagesByGroupNotExistException::new);

        logger.info("MessageService - findByGroup - End; ");
        Set<MessageDTO> messageDTOSet = new HashSet<>();

        for (Message message: messages.get()){
            messageDTOSet.add(MessageMapper.INSTANCE.messageToMessageDto(message));
        }
        return messageDTOSet;
    }

    @Override
    public void send(MessageDTO messageDTO) {

        Message message = MessageMapper.INSTANCE.messageDtoToMessage(messageDTO);

        Optional<Contact> contact = contactRepository.findById(message.getContact().getId());
        contact.orElseThrow(ContactNotExistException::new);

        Optional<Group> group = groupRepository.findById(message.getGroup().getId());
        group.orElseThrow(GroupNotExistException::new);

        message.setCreatedAt(new Date());
        messageRepository.save(message);

        Notification notification = new Notification();
        notification.setCreatedAt(new Date());
        notification.setIsRead(0);
        notification.setGroup(message.getGroup());


        if (contact.get().getCellPhoneNumber().equals("")){
            notification.setMessage("You have a new message from " + contact.get().getCellPhoneNumber());
        }else{
            notification.setMessage("You have a new message from " + contact.get().getAlias());
        }
        notificationRepository.save(notification);

    }

    @Override
    public void deleteById(long id) {
        logger.info("MessageService - deleteById - Begin; {}", id);
        Optional<Message> message = messageRepository.findById(id);
        message.orElseThrow(MessageNotExistException::new);

        messageRepository.delete(message.get());

    }

    @Override
    public void deleteByGroupId(long groupId) {
        logger.info("MessageService - deleteByGroupId - Begin; {}", groupId);

        Optional<Group> group = groupRepository.findById(groupId);
        group.orElseThrow(GroupNotExistException::new);

        Optional<List<Message>> messages = messageRepository.findAllByGroup(group.get());
        messages.orElseThrow(MessagesByGroupNotExistException::new);

        for(Message message: messages.get()){
            messageRepository.delete(message);
        }

    }

    @Override
    public void markRead(long id){
        logger.info("MessageService - markRead - Begin; {}", id);
        Optional<Message> message = messageRepository.findById(id);
        message.orElseThrow(MessageNotExistException::new);

        Message messageUpdate = message.get();
        messageUpdate.setIsRead(1);

        messageRepository.save(messageUpdate);
    }
}

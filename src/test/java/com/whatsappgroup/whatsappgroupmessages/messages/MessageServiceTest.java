package com.whatsappgroup.whatsappgroupmessages.messages;

import com.whatsappgroup.whatsappgroupmessages.models.Contact;
import com.whatsappgroup.whatsappgroupmessages.models.Group;
import com.whatsappgroup.whatsappgroupmessages.models.Message;
import com.whatsappgroup.whatsappgroupmessages.models.Notification;
import com.whatsappgroup.whatsappgroupmessages.repositories.IContactRepository;
import com.whatsappgroup.whatsappgroupmessages.repositories.IGroupRepository;
import com.whatsappgroup.whatsappgroupmessages.repositories.IMessageRepository;
import com.whatsappgroup.whatsappgroupmessages.repositories.INotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

@SpringBootTest
public class MessageServiceTest {

    @Autowired
    private IMessageRepository messageRepository;

    @Autowired
    private IGroupRepository groupRepository;

    @Autowired
    private IContactRepository contactRepository;

    @Autowired
    private INotificationRepository notificationRepository;

    @Test
    void testSend(){
        Message message = new Message();
        message.setMessage("Esto es una prueba de envio de mensaje");
        message.setCreatedAt(new Date());

        Group group = groupRepository.findById(1L).get();
        Assertions.assertNotNull(group);

        message.setGroup(group);
        Contact contact = contactRepository.findById(1L).get();

        Assertions.assertNotNull(contact);

        message.setContact(contact);
        message.setGroup(group);
        message.setIsRead(0);
        message.setId(1L);

        Message messageSend = messageRepository.save(message);

        Assertions.assertNotNull(messageSend);

        Notification notification = new Notification();
        notification.setCreatedAt(new Date());
        notification.setIsRead(0);
        notification.setGroup(message.getGroup());

        if (contact.getCellPhoneNumber().equals("")){
            notification.setMessage("You have a new message from " + contact.getCellPhoneNumber());
        }else{
            notification.setMessage("You have a new message from " + contact.getAlias());
        }
        Notification notificationSave = notificationRepository.save(notification);
        Assertions.assertNotNull(notificationSave);

    }

}

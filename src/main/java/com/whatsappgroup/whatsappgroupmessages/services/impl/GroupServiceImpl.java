package com.whatsappgroup.whatsappgroupmessages.services.impl;

import com.whatsappgroup.whatsappgroupmessages.controllers.exception.contact.ContactNotExistException;
import com.whatsappgroup.whatsappgroupmessages.controllers.exception.group.GroupNotExistException;
import com.whatsappgroup.whatsappgroupmessages.dtos.GroupDTO;
import com.whatsappgroup.whatsappgroupmessages.mappers.GroupMapper;
import com.whatsappgroup.whatsappgroupmessages.models.Contact;
import com.whatsappgroup.whatsappgroupmessages.models.Group;
import com.whatsappgroup.whatsappgroupmessages.models.Message;
import com.whatsappgroup.whatsappgroupmessages.repositories.IContactRepository;
import com.whatsappgroup.whatsappgroupmessages.repositories.IGroupRepository;
import com.whatsappgroup.whatsappgroupmessages.services.IGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class GroupServiceImpl implements IGroupService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IGroupRepository groupRepository;

    @Autowired
    private IContactRepository contactRepository;

    @Override
    public GroupDTO findByGroupId(long group_id) {
        logger.info("GroupService - findByGroupId - Begin; {}", group_id);
        Optional<Group> group = groupRepository.findById(group_id);

        group.orElseThrow(GroupNotExistException::new);

        logger.info("GroupService - findByGroupId - End; ");
        return GroupMapper.INSTANCE.groupToGroupDto(group.get());
    }

    @Override
    public void save(GroupDTO groupDTO){

        Group group = GroupMapper.INSTANCE.groupDtoToGroup(groupDTO);

        groupRepository.save(group);
    }

    @Override
    public void addContact(long idGroup, long idContact){
        logger.info("GroupService - addContact - Begin; {}",idContact);

        Optional<Group> group = groupRepository.findById(idGroup);
        group.orElseThrow(GroupNotExistException::new);

        Optional<Contact> contact = contactRepository.findById(idContact);
        contact.orElseThrow(ContactNotExistException::new);

        Group groupUpdate = group.get();
        Set<Contact> contacts = new HashSet<>();
        contacts.add(contact.get());
        groupUpdate.setContacts(contacts);

        groupRepository.save(groupUpdate);
    }

}

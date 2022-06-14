package com.whatsappgroup.whatsappgroupmessages.services;

import com.whatsappgroup.whatsappgroupmessages.dtos.GroupDTO;

public interface IGroupService {
    GroupDTO findByGroupId(long group_id);
    void save(GroupDTO groupDTO);
    void addContact(long idGroup, long idContact);
}

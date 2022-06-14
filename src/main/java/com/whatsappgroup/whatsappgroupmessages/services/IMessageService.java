package com.whatsappgroup.whatsappgroupmessages.services;

import com.whatsappgroup.whatsappgroupmessages.dtos.MessageDTO;

import java.util.Set;


public interface IMessageService {
    MessageDTO findByMessageId(long message_id);
    Set<MessageDTO> findByContactAndGroup(long contact, long group);
    Set<MessageDTO> findByGroup(long group);
    void send(MessageDTO messageDTO);
    void deleteByGroupId(long groupId);
    void deleteById(long notificationId);
    void markRead(long id);
}

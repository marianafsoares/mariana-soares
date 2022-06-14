package com.whatsappgroup.whatsappgroupmessages.services;

import com.whatsappgroup.whatsappgroupmessages.dtos.NotificationDTO;

import java.util.Set;


public interface INotificationService {
    NotificationDTO findByNotificationId(long notification_id);
    Set<NotificationDTO> findByGroup(long group);
    void save(NotificationDTO messageDTO);
    void deleteByGroupId(long groupId);
    void deleteById(long notificationId);
    void markRead(long id);
}

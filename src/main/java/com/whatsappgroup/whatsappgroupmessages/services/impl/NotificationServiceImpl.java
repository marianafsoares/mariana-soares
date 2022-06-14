package com.whatsappgroup.whatsappgroupmessages.services.impl;

import com.whatsappgroup.whatsappgroupmessages.controllers.exception.group.GroupNotExistException;
import com.whatsappgroup.whatsappgroupmessages.controllers.exception.notification.NotificationByGroupNotExistException;
import com.whatsappgroup.whatsappgroupmessages.controllers.exception.notification.NotificationNotExistException;
import com.whatsappgroup.whatsappgroupmessages.dtos.NotificationDTO;
import com.whatsappgroup.whatsappgroupmessages.mappers.NotificationMapper;
import com.whatsappgroup.whatsappgroupmessages.models.Group;
import com.whatsappgroup.whatsappgroupmessages.models.Notification;
import com.whatsappgroup.whatsappgroupmessages.repositories.IGroupRepository;
import com.whatsappgroup.whatsappgroupmessages.repositories.INotificationRepository;
import com.whatsappgroup.whatsappgroupmessages.services.INotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class NotificationServiceImpl implements INotificationService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private INotificationRepository notificationRepository;

    @Autowired
    private IGroupRepository groupRepository;


    @Override
    public NotificationDTO findByNotificationId(long notification_id) {
        logger.info("NotificationService - findByNotificationId - Begin; {}", notification_id);
        Optional<Notification> notification = notificationRepository.findById(notification_id);

        notification.orElseThrow(NotificationNotExistException::new);

        logger.info("NotificationService - findByNotificationId - End; ");
        return NotificationMapper.INSTANCE.notificationToNotificationDto(notification.get());
    }

    @Override
    public Set<NotificationDTO> findByGroup(long groupId) {
        logger.info("NotificationService - findByGroup - Begin; {}", groupId);

        Optional<Group> group = groupRepository.findById(groupId);
        group.orElseThrow(GroupNotExistException::new);

        Optional<List<Notification>> notifications = notificationRepository.findAllByGroup(group.get());

        notifications.orElseThrow(NotificationByGroupNotExistException::new);

        logger.info("NotificationService - findByGroup - End; ");
        Set<NotificationDTO> notificationDTOSet = new HashSet<>();

        for (Notification notification: notifications.get()){
            notificationDTOSet.add(NotificationMapper.INSTANCE.notificationToNotificationDto(notification));
        }
        return notificationDTOSet;
    }

    @Override
    public void save(NotificationDTO notificationDTO) {
        Notification notification = NotificationMapper.INSTANCE.notificationDtoToNotification(notificationDTO);
        notificationRepository.save(notification);
    }

    @Override
    public void deleteById(long id) {

        Optional<Notification> notification = notificationRepository.findById(id);
        notification.orElseThrow(NotificationNotExistException::new);

        notificationRepository.delete(notification.get());

    }

    @Override
    public void deleteByGroupId(long groupId) {
        logger.info("NotificationService - deleteByGroupId - Begin; {}", groupId);

        Optional<Group> group = groupRepository.findById(groupId);
        group.orElseThrow(GroupNotExistException::new);

        Optional<List<Notification>> notifications = notificationRepository.findAllByGroup(group.get());
        notifications.orElseThrow(NotificationByGroupNotExistException::new);

        for(Notification notification: notifications.get()){
            notificationRepository.delete(notification);
        }

    }

    @Override
    public void markRead(long id){
        logger.info("NotificationService - markRead - Begin; {}", id);
        Optional<Notification> notification = notificationRepository.findById(id);
        notification.orElseThrow(NotificationNotExistException::new);

        Notification notificationUpdate = notification.get();
        notificationUpdate.setIsRead(1);

        notificationRepository.save(notificationUpdate);
    }
}

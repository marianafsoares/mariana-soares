package com.whatsappgroup.whatsappgroupmessages.mappers;

import com.whatsappgroup.whatsappgroupmessages.dtos.NotificationDTO;
import com.whatsappgroup.whatsappgroupmessages.models.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NotificationMapper {

    NotificationMapper INSTANCE = Mappers.getMapper( NotificationMapper.class );

    NotificationDTO notificationToNotificationDto(Notification notification);
    Notification notificationDtoToNotification(NotificationDTO notificationDTO);
}

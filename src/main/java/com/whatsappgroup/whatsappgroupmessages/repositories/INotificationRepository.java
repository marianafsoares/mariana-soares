package com.whatsappgroup.whatsappgroupmessages.repositories;

import com.whatsappgroup.whatsappgroupmessages.models.Group;
import com.whatsappgroup.whatsappgroupmessages.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface INotificationRepository extends JpaRepository<Notification, Long> {

    Optional<List<Notification>> findAllByGroup(Group group);
}

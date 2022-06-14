package com.whatsappgroup.whatsappgroupmessages.repositories;

import com.whatsappgroup.whatsappgroupmessages.models.Contact;
import com.whatsappgroup.whatsappgroupmessages.models.Group;
import com.whatsappgroup.whatsappgroupmessages.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IMessageRepository extends JpaRepository<Message, Long> {
    Optional<List<Message>> findAllByContactAndGroup(Contact contact, Group group);
    Optional<List<Message>> findAllByGroup(Group group);

}

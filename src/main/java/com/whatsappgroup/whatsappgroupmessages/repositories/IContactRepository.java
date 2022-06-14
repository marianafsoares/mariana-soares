package com.whatsappgroup.whatsappgroupmessages.repositories;

import com.whatsappgroup.whatsappgroupmessages.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByCellPhoneNumber(String cellPhoneNumber);
    Optional<Contact> findByAlias(String alias);
}

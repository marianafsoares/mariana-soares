package com.whatsappgroup.whatsappgroupmessages.repositories;

import com.whatsappgroup.whatsappgroupmessages.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IGroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findByName(String name);
}

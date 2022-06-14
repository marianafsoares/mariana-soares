package com.whatsappgroup.whatsappgroupmessages.services;

import com.whatsappgroup.whatsappgroupmessages.dtos.ContactDTO;

import java.util.List;
import java.util.Set;

public interface IContactService {
    ContactDTO findByContactId(long contact_id);
    ContactDTO findByCellPhoneNumber(String cellPhone);
    ContactDTO findByAlias(String alias);
    void save(ContactDTO contactDTO);
    Set<ContactDTO> getAll();

}

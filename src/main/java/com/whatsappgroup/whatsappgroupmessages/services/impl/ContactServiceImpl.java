package com.whatsappgroup.whatsappgroupmessages.services.impl;

import com.whatsappgroup.whatsappgroupmessages.controllers.exception.contact.ContactNotExistException;
import com.whatsappgroup.whatsappgroupmessages.dtos.ContactDTO;
import com.whatsappgroup.whatsappgroupmessages.mappers.ContactMapper;
import com.whatsappgroup.whatsappgroupmessages.models.Contact;
import com.whatsappgroup.whatsappgroupmessages.repositories.IContactRepository;
import com.whatsappgroup.whatsappgroupmessages.services.IContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ContactServiceImpl implements IContactService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IContactRepository contactRepository;
    @Override
    public ContactDTO findByContactId(long contact_id) {
        logger.info("ContactService - findByContactId - Begin; {}", contact_id);
        Optional<Contact> contact = contactRepository.findById(contact_id);

        contact.orElseThrow(ContactNotExistException::new);

        logger.info("ContactService - findByContactId - End; ");
        return ContactMapper.INSTANCE.contactToContactDto(contact.get());
    }

    @Override
    public ContactDTO findByCellPhoneNumber(String cellPhone) {
        logger.info("ContactService - findByCellPhoneNumber - Begin; {}", cellPhone);
        Optional<Contact> contact = contactRepository.findByCellPhoneNumber(cellPhone);

        contact.orElseThrow(ContactNotExistException::new);

        logger.info("ContactService - findByCellPhoneNumber - End; ");
        return ContactMapper.INSTANCE.contactToContactDto(contact.get());
    }

    @Override
    public ContactDTO findByAlias(String alias) {
        logger.info("ContactService - findByAlias - Begin; {}", alias);
        Optional<Contact> contact = contactRepository.findByAlias(alias);

        contact.orElseThrow(ContactNotExistException::new);

        logger.info("ContactService - findByAlias - End; ");
        return ContactMapper.INSTANCE.contactToContactDto(contact.get());
    }

    @Override
    public void save(ContactDTO contactDTO) {
        Contact contact = ContactMapper.INSTANCE.contactDtoToContact(contactDTO);
        contactRepository.save(contact);
    }

    @Override
    public Set<ContactDTO> getAll(){
        logger.info("ContactService - getAll - Begin");

        List<Contact> contacts = contactRepository.findAll();

        logger.info("ContactService - getAll - End; ");

        Set<ContactDTO> contactDTOSet = new HashSet<>();

        for(Contact contact : contacts){
            contactDTOSet.add(ContactMapper.INSTANCE.contactToContactDto(contact));
        }

        return contactDTOSet;
    }
}

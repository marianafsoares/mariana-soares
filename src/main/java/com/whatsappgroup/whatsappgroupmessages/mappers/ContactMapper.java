package com.whatsappgroup.whatsappgroupmessages.mappers;

import com.whatsappgroup.whatsappgroupmessages.dtos.ContactDTO;
import com.whatsappgroup.whatsappgroupmessages.models.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ContactMapper {

    ContactMapper INSTANCE = Mappers.getMapper( ContactMapper.class );

    ContactDTO contactToContactDto(Contact contact);
    Contact contactDtoToContact(ContactDTO contactDTO);
}

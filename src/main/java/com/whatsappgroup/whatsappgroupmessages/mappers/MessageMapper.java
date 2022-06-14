package com.whatsappgroup.whatsappgroupmessages.mappers;

import com.whatsappgroup.whatsappgroupmessages.dtos.MessageDTO;
import com.whatsappgroup.whatsappgroupmessages.models.Message;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageMapper {

    MessageMapper INSTANCE = Mappers.getMapper( MessageMapper.class );

    MessageDTO messageToMessageDto(Message message);
    Message messageDtoToMessage(MessageDTO messageDTO);
}

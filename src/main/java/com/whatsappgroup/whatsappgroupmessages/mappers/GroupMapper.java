package com.whatsappgroup.whatsappgroupmessages.mappers;

import com.whatsappgroup.whatsappgroupmessages.dtos.GroupDTO;
import com.whatsappgroup.whatsappgroupmessages.models.Group;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface GroupMapper {

    GroupMapper INSTANCE = Mappers.getMapper( GroupMapper.class );
    GroupDTO groupToGroupDto(Group group);
    Group groupDtoToGroup(GroupDTO groupDTO);
}

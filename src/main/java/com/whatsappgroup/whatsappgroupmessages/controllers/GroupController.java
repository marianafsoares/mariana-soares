package com.whatsappgroup.whatsappgroupmessages.controllers;

import com.whatsappgroup.whatsappgroupmessages.controllers.exception.BaseResponse;
import com.whatsappgroup.whatsappgroupmessages.dtos.GroupDTO;
import com.whatsappgroup.whatsappgroupmessages.services.IGroupService;
import com.whatsappgroup.whatsappgroupmessages.utils.ResponseCodes;
import com.whatsappgroup.whatsappgroupmessages.utils.ResponseFactory;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    IGroupService groupService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value = "Return whatsApp group by groupId ", response = GroupDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 400, message = "[101 - \"The group does not exist\"]")})
    @GetMapping(value = "/{group_id}", produces = "application/json")
    public GroupDTO getGroupById(@ApiParam(value = "group_id", required = true) @PathVariable long group_id){
        return groupService.findByGroupId(group_id);
    }

    @ApiOperation(value = "Create a new whatsApp group", response = GroupDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 400, message = "[\n102 - \"Failed to create a group \", \n"
    )})
    @PostMapping(consumes = "application/json", produces = "application/json")
    public BaseResponse save (@Valid @RequestBody GroupDTO groupDTO){
        BaseResponse baseResponse;
        groupService.save(groupDTO);
        baseResponse = ResponseFactory.createResponse(ResponseCodes.GROUP_SAVE_OK);

        return baseResponse;
    }

    @ApiOperation(value = "Add contact to whatsApp group", response = GroupDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 400, message = "[\n101 - \"The group does not exist \", \n" +
            "[\n201 - \"The contact does not exist \", \n"
    )})
    @PutMapping(value = "/addContact", produces = "application/json")
    public BaseResponse addContacts (@ApiParam(value = "group_id", required = true) @RequestParam long group_id, @ApiParam(value = "contact_id", required = true) @RequestParam long contact_id){
        BaseResponse baseResponse;
        groupService.addContact(group_id, contact_id);
        baseResponse = ResponseFactory.createResponse(ResponseCodes.CONTACT_ADD_GROUP_OK);

        return baseResponse;
    }
}

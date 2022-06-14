package com.whatsappgroup.whatsappgroupmessages.controllers;

import com.whatsappgroup.whatsappgroupmessages.controllers.exception.BaseResponse;
import com.whatsappgroup.whatsappgroupmessages.dtos.MessageDTO;
import com.whatsappgroup.whatsappgroupmessages.services.IMessageService;
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
import java.util.Set;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    IMessageService messageService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value = "Return message by id ", response = MessageDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 400, message = "[301 - \"The message does not exist\"]")})
    @GetMapping(value = "/{message_id}", produces = "application/json")
    public MessageDTO getMessageById(@ApiParam(value = "message_id", required = true) @PathVariable long message_id){
        return messageService.findByMessageId(message_id);
    }

    @ApiOperation(value = "Return message by contactId and groupId ", response = MessageDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 400, message = "[\n303 - \"There are no messages for this group and this contact \", \n," +
            "\n100 - \"The group does not exist \", \n," +
            "\n200 - \"The contact does not exist \", \n,"
    )})
    @GetMapping(value = "/byContactAndGroup/{contact_id}/{group_id}", produces = "application/json")
    public Set<MessageDTO> getMessageByContactAndGroup(@ApiParam(value = "contact_id", required = true) @PathVariable long contact_id, @ApiParam(value = "group_id", required = true) @PathVariable long group_id){
        return messageService.findByContactAndGroup(contact_id, group_id);
    }

    @ApiOperation(value = "Return message by groupId ", response = MessageDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 400, message = "[\n304 - \"There are no messages for this group \", \n," +
            "\n100 - \"The group does not exist \", \n,"
    )})
    @GetMapping(value = "/byGroup/{group_id}", produces = "application/json")
    public Set<MessageDTO> getMessageByGroup(@ApiParam(value = "group_id", required = true) @PathVariable long group_id){
        return messageService.findByGroup(group_id);
    }

    @ApiOperation(value = "Send a message and create a notification for this message", response = MessageDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 400, message = "[\n302 - \"Failed to send a message \", \n," +
            "\n100 - \"The group does not exist \", \n," +
            "\n200 - \"The contact does not exist \", \n,"
    )})
    @PostMapping(value = "/send", consumes = "application/json", produces = "application/json")
    public BaseResponse send (@Valid @RequestBody MessageDTO messageDTO){
        BaseResponse baseResponse;
        messageService.send(messageDTO);
        baseResponse = ResponseFactory.createResponse(ResponseCodes.MESSAGE_SEND_OK);

        return baseResponse;
    }

    @ApiOperation(value = "Delete messages by groupId")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "[\n305 - \"Failed to delete messages for this group \", \n," +
            "\n100 - \"The group does not exist \", \n," +
            "\n304 - \"There are no messages for this group \", \n,"

    )})
    @DeleteMapping(value = "/byGroup/{group_id}")
    public BaseResponse deleteByGroupId (@ApiParam(value = "group_id", required = true) @PathVariable long group_id){
        BaseResponse baseResponse;
        messageService.deleteByGroupId(group_id);
        baseResponse = ResponseFactory.createResponse(ResponseCodes.MESSAGE_DELETE_OK);

        return baseResponse;
    }

    @ApiOperation(value = "Delete notificacion by id")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "[\n404 - \"Failed to delete message \", \n," +
            "\n301 - \"The message does not exist \", \n,"
    )})
    @DeleteMapping(value = "/{message_id}")
    public BaseResponse deleteById (@ApiParam(value = "message_id", required = true) @PathVariable long message_id){
        BaseResponse baseResponse;
        messageService.deleteById(message_id);
        baseResponse = ResponseFactory.createResponse(ResponseCodes.MESSAGE_DELETE_OK);

        return baseResponse;
    }

    @ApiOperation(value = "Mark read message by id")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "\n302 - \"The message does not exist \", \n,"
    )})
    @PutMapping(value = "/markRead/{message_id}")
    public BaseResponse markRead (@ApiParam(value = "message_id", required = true) @PathVariable long message_id){
        BaseResponse baseResponse;
        messageService.markRead(message_id);
        baseResponse = ResponseFactory.createResponse(ResponseCodes.MESSAGE_MARK_READ_OK);

        return baseResponse;
    }
}

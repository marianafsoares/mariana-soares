package com.whatsappgroup.whatsappgroupmessages.controllers;

import com.whatsappgroup.whatsappgroupmessages.controllers.exception.BaseResponse;
import com.whatsappgroup.whatsappgroupmessages.dtos.NotificationDTO;
import com.whatsappgroup.whatsappgroupmessages.services.INotificationService;
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

import java.util.Set;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    INotificationService notificationService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value = "Return notification by id ", response = NotificationDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 400, message = "[400 - \"The notification does not exist\"]")})
    @GetMapping(value = "/{notification_id}", produces = "application/json")
    public NotificationDTO getNotificationById(@ApiParam(value = "notification_id", required = true) @PathVariable long notification_id){
        return notificationService.findByNotificationId(notification_id);
    }


    @ApiOperation(value = "Return notification by groupId ", response = NotificationDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 400, message = "[\n403 - \"There are no notifications for this group \", \n," +
            "\n100 - \"The group does not exist \", \n,"
    )})
    @GetMapping(value = "/byGroup/{group_id}", produces = "application/json")
    public Set<NotificationDTO> getNotificationByGroup(@ApiParam(value = "group_id", required = true) @PathVariable long group_id){
        return notificationService.findByGroup(group_id);
    }

    @ApiOperation(value = "Delete notificacions by groupId")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "[\n403 - \"Failed to delete notifications \", \n," +
            "\n100 - \"The group does not exist \", \n," +
            "\n402 - \"There are no notifications for this group \", \n,"
    )})
    @DeleteMapping(value = "/byGroup/{group_id}")
    public BaseResponse deleteByGroupId (@ApiParam(value = "group_id", required = true) @PathVariable long group_id){
        BaseResponse baseResponse;
        notificationService.deleteByGroupId(group_id);
        baseResponse = ResponseFactory.createResponse(ResponseCodes.NOTIFICATION_DELETE_OK);

        return baseResponse;
    }

    @ApiOperation(value = "Delete notificacion by id")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "[\n404 - \"Failed to delete notification \", \n," +
            "\n400 - \"The notification does not exist \", \n,"
    )})
    @DeleteMapping(value = "/{notification_id}")
    public BaseResponse deleteById (@ApiParam(value = "notification_id", required = true) @PathVariable long notification_id){
        BaseResponse baseResponse;
        notificationService.deleteById(notification_id);
        baseResponse = ResponseFactory.createResponse(ResponseCodes.NOTIFICATION_DELETE_OK);

        return baseResponse;
    }

    @ApiOperation(value = "Mark read notificacion by id")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "\n400 - \"The notification does not exist \", \n,"
    )})
    @PutMapping(value = "/markRead/{notification_id}")
    public BaseResponse markRead (@ApiParam(value = "notification_id", required = true) @PathVariable long notification_id){
        BaseResponse baseResponse;
        notificationService.markRead(notification_id);
        baseResponse = ResponseFactory.createResponse(ResponseCodes.NOTIFICATION_MARK_READ_OK);

        return baseResponse;
    }
}

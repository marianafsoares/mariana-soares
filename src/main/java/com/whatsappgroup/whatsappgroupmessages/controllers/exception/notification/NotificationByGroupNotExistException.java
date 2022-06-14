package com.whatsappgroup.whatsappgroupmessages.controllers.exception.notification;


import com.whatsappgroup.whatsappgroupmessages.utils.ResponseCodes;

public class NotificationByGroupNotExistException extends RuntimeException {
    private ResponseCodes responseCode = ResponseCodes.NOTIFICATION_BY_GROUP_NOT_EXIST;

    public NotificationByGroupNotExistException() {
        super(ResponseCodes.NOTIFICATION_BY_GROUP_NOT_EXIST.getReason());
    }

    public ResponseCodes getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCodes responseCode) {
        this.responseCode = responseCode;
    }
}

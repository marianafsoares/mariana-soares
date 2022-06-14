package com.whatsappgroup.whatsappgroupmessages.controllers.exception.notification;


import com.whatsappgroup.whatsappgroupmessages.utils.ResponseCodes;

public class NotificationNotExistException extends RuntimeException {
    private ResponseCodes responseCode = ResponseCodes.NOTIFICATION_NOT_EXIST;

    public NotificationNotExistException() {
        super(ResponseCodes.NOTIFICATION_NOT_EXIST.getReason());
    }

    public ResponseCodes getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCodes responseCode) {
        this.responseCode = responseCode;
    }
}

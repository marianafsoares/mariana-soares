package com.whatsappgroup.whatsappgroupmessages.controllers.exception.notification;


import com.whatsappgroup.whatsappgroupmessages.utils.ResponseCodes;

public class NotificationDeleteException extends RuntimeException {
    private ResponseCodes responseCode = ResponseCodes.NOTIFICATION_DELETE_BY_ID_FAIL;

    public NotificationDeleteException() {
        super(ResponseCodes.NOTIFICATION_DELETE_BY_ID_FAIL.getReason());
    }

    public ResponseCodes getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCodes responseCode) {
        this.responseCode = responseCode;
    }
}

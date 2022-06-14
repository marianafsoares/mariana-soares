package com.whatsappgroup.whatsappgroupmessages.controllers.exception.notification;


import com.whatsappgroup.whatsappgroupmessages.utils.ResponseCodes;

public class NotificationDeleteByGroupException extends RuntimeException {
    private ResponseCodes responseCode = ResponseCodes.NOTIFICATION_DELETE_BY_GROUP_ID_FAIL;

    public NotificationDeleteByGroupException() {
        super(ResponseCodes.NOTIFICATION_DELETE_BY_GROUP_ID_FAIL.getReason());
    }

    public ResponseCodes getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCodes responseCode) {
        this.responseCode = responseCode;
    }
}

package com.whatsappgroup.whatsappgroupmessages.controllers.exception.notification;


import com.whatsappgroup.whatsappgroupmessages.utils.ResponseCodes;

public class NotificationSaveException extends RuntimeException {
    private ResponseCodes responseCode = ResponseCodes.NOTIFICATION_SAVE_FAIL;

    public NotificationSaveException() {
        super(ResponseCodes.NOTIFICATION_SAVE_FAIL.getReason());
    }

    public ResponseCodes getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCodes responseCode) {
        this.responseCode = responseCode;
    }
}

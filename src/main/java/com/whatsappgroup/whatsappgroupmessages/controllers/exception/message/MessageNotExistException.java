package com.whatsappgroup.whatsappgroupmessages.controllers.exception.message;


import com.whatsappgroup.whatsappgroupmessages.utils.ResponseCodes;

public class MessageNotExistException extends RuntimeException {
    private ResponseCodes responseCode = ResponseCodes.MESSAGE_NOT_EXIST;

    public MessageNotExistException() {
        super(ResponseCodes.MESSAGE_NOT_EXIST.getReason());
    }

    public ResponseCodes getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCodes responseCode) {
        this.responseCode = responseCode;
    }
}

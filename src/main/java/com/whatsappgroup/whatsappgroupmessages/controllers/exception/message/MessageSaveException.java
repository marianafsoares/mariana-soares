package com.whatsappgroup.whatsappgroupmessages.controllers.exception.message;


import com.whatsappgroup.whatsappgroupmessages.utils.ResponseCodes;

public class MessageSaveException extends RuntimeException {
    private ResponseCodes responseCode = ResponseCodes.MESSAGE_SAVE_FAIL;

    public MessageSaveException() {
        super(ResponseCodes.MESSAGE_SAVE_FAIL.getReason());
    }

    public ResponseCodes getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCodes responseCode) {
        this.responseCode = responseCode;
    }
}

package com.whatsappgroup.whatsappgroupmessages.controllers.exception.message;


import com.whatsappgroup.whatsappgroupmessages.utils.ResponseCodes;

public class MessageDeleteException extends RuntimeException {
    private ResponseCodes responseCode = ResponseCodes.MESSAGE_DELETE_BY_ID_FAIL;

    public MessageDeleteException() {
        super(ResponseCodes.MESSAGE_DELETE_BY_ID_FAIL.getReason());
    }

    public ResponseCodes getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCodes responseCode) {
        this.responseCode = responseCode;
    }
}

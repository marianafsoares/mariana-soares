package com.whatsappgroup.whatsappgroupmessages.controllers.exception.message;


import com.whatsappgroup.whatsappgroupmessages.utils.ResponseCodes;

public class MessageDeleteByGroupException extends RuntimeException {
    private ResponseCodes responseCode = ResponseCodes.MESSAGE_DELETE_BY_GROUP_ID_FAIL;

    public MessageDeleteByGroupException() {
        super(ResponseCodes.MESSAGE_DELETE_BY_GROUP_ID_FAIL.getReason());
    }

    public ResponseCodes getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCodes responseCode) {
        this.responseCode = responseCode;
    }
}

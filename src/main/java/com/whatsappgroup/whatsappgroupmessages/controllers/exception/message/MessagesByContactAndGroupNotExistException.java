package com.whatsappgroup.whatsappgroupmessages.controllers.exception.message;


import com.whatsappgroup.whatsappgroupmessages.utils.ResponseCodes;

public class MessagesByContactAndGroupNotExistException extends RuntimeException {
    private ResponseCodes responseCode = ResponseCodes.MESSAGES_BY_CONTACT_AND_GROUP_NOT_EXIST;

    public MessagesByContactAndGroupNotExistException() {
        super(ResponseCodes.MESSAGES_BY_CONTACT_AND_GROUP_NOT_EXIST.getReason());
    }

    public ResponseCodes getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCodes responseCode) {
        this.responseCode = responseCode;
    }
}

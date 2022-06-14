package com.whatsappgroup.whatsappgroupmessages.controllers.exception.group;


import com.whatsappgroup.whatsappgroupmessages.utils.ResponseCodes;

public class GroupNotExistException extends RuntimeException {
    private ResponseCodes responseCode = ResponseCodes.GROUP_NOT_EXIST;

    public GroupNotExistException() {
        super(ResponseCodes.GROUP_NOT_EXIST.getReason());
    }

    public ResponseCodes getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCodes responseCode) {
        this.responseCode = responseCode;
    }
}

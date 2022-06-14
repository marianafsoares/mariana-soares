package com.whatsappgroup.whatsappgroupmessages.controllers.exception.group;


import com.whatsappgroup.whatsappgroupmessages.utils.ResponseCodes;

public class GroupSaveException extends RuntimeException {
    private ResponseCodes responseCode = ResponseCodes.GROUP_SAVE_FAIL;

    public GroupSaveException() {
        super(ResponseCodes.GROUP_SAVE_FAIL.getReason());
    }

    public ResponseCodes getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCodes responseCode) {
        this.responseCode = responseCode;
    }
}

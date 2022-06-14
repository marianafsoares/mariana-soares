package com.whatsappgroup.whatsappgroupmessages.controllers.exception.contact;


import com.whatsappgroup.whatsappgroupmessages.utils.ResponseCodes;

public class ContactNotExistException extends RuntimeException {
    private ResponseCodes responseCode = ResponseCodes.CONTACT_NOT_EXIST;

    public ContactNotExistException() {
        super(ResponseCodes.CONTACT_NOT_EXIST.getReason());
    }

    public ResponseCodes getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCodes responseCode) {
        this.responseCode = responseCode;
    }
}

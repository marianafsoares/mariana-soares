package com.whatsappgroup.whatsappgroupmessages.controllers.exception.contact;


import com.whatsappgroup.whatsappgroupmessages.utils.ResponseCodes;

public class ContactSaveException extends RuntimeException {
    private ResponseCodes responseCode = ResponseCodes.CONTACT_SAVE_FAIL;

    public ContactSaveException() {
        super(ResponseCodes.CONTACT_SAVE_FAIL.getReason());
    }

    public ResponseCodes getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCodes responseCode) {
        this.responseCode = responseCode;
    }
}

package com.whatsappgroup.whatsappgroupmessages.controllers.exception;

import com.whatsappgroup.whatsappgroupmessages.utils.ResponseCodes;

public class BaseResponse {

    private int code;
    private String message;

    public BaseResponse() {
    }

    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResponse(ResponseCodes responseCode) {
        this.code = responseCode.getValue();
        this.message = responseCode.getReason();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

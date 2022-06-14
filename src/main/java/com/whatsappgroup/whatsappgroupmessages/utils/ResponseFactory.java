package com.whatsappgroup.whatsappgroupmessages.utils;


import com.whatsappgroup.whatsappgroupmessages.controllers.exception.BaseResponse;

/**
 * Created by Mariana Soares.
 */
public class ResponseFactory {
    public static BaseResponse createResponse(ResponseCodes responseCodes){
        return new BaseResponse(responseCodes.getValue(), responseCodes.getReason());

    }
}

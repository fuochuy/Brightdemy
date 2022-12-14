package hcmus.brightdemy.common.utils;

import hcmus.brightdemy.constant.ResponseMessage;

import java.util.HashMap;

public class ResponseUtils {
    public static HashMap<String, Object> buildResponse(String msgCode, Object data) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("code", ResponseMessage.getCode(msgCode));
        response.put("message", ResponseMessage.getMessage(msgCode));
        response.put("data", data);
        return response;
    }

    public static HashMap<String, Object> buildResponse(String msgCode) {
        return buildResponse(msgCode, null);
    }
}

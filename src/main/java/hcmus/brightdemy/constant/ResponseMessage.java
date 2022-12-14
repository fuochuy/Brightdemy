package hcmus.brightdemy.constant;

import hcmus.brightdemy.common.utils.ServiceUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ResponseMessage {
    public static final String SUCCESSFUL = "SUCCESSFUL";
    public static final int SUCCESS_CODE = 200;
    public static final String SUCCESS_MESSAGE = "success";

    public static final String FAIL = "FAIL";
    public static final int FAIL_CODE = 1;
    public static final String FAIL_MESSAGE = "fail";

    public static final String LOGIN_FAIL = "LOGIN_FAIL";
    public static final String OBJECT_CREATE_EMPTY_FIELD = "OBJECT_CREATE_EMPTY_FIELD";
    public static final String OBJECT_UPDATE_EMPTY_FIELD = "OBJECT_UPDATE_EMPTY_FIELD";
    public static final String USER_CREATE_EXISTING = "USER_CREATE_EXISTING";
    public static final String USER_NOT_EXISTED = "USER_NOT_EXISTED";
    public static final String RULE_NOT_EXISTED = "RULE_NOT_EXISTED";
    public static final String USER_HAS_BEEN_LOCKED = "USER_HAS_BEEN_LOCKED";
    public static final String INVALID_DATE = "INVALID_DATE";

    public static final String INVALID_PARAMS = "invalid params";
    public static final String DATE_RANGE_EXCEPTION = "Date Range Exception";
    public static final String SERVICE_ERROR = "Service error: ";


    private static final Map<String, List> MESSAGES = new HashMap<String, List>() {{
        put(SUCCESSFUL, Arrays.asList(SUCCESS_CODE, "success"));

        put(FAIL, Arrays.asList(FAIL_CODE, "failure"));
        put(LOGIN_FAIL, Arrays.asList(FAIL_CODE, "Login failed"));
        put(OBJECT_CREATE_EMPTY_FIELD, Arrays.asList(FAIL_CODE, "Fields are not empty"));
        put(USER_CREATE_EXISTING, Arrays.asList(FAIL_CODE, "User existed"));
        put(USER_NOT_EXISTED, Arrays.asList(FAIL_CODE, "User not existed"));
        put(RULE_NOT_EXISTED, Arrays.asList(FAIL_CODE, "Rule not existed"));
        put(USER_HAS_BEEN_LOCKED, Arrays.asList(2, "User has been locked"));
        put(INVALID_DATE, Arrays.asList(FAIL_CODE, "Invalid date"));
        put(OBJECT_UPDATE_EMPTY_FIELD, Arrays.asList(FAIL_CODE, "Fields are not empty"));
    }};

    public static Integer getCode(String key) {
        return Integer.parseInt(ServiceUtils.parseStringToList(MESSAGES, key).get(0));
    }

    public static String getMessage(String key) {
        return ServiceUtils.parseStringToList(MESSAGES, key).get(1);
    }
}

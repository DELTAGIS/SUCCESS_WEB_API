package com.deltagis.success.adapters.web.api;


public class ApiEndpoints {
    public static final String BASE_API = "/api";
    public static final String USERS = BASE_API + "/users";

    public static final String REGISTER_USER = "/register";
    public static final String REGISTER_USER_ENDPOINT = USERS + REGISTER_USER;

    public static final String LOGIN_USER = USERS + "/login";
    public static final String LOGIN_USER_ENDPOINT = USERS + LOGIN_USER;

    public static final String SWAGGER_UI = "/swagger-ui.html";

    public static final String SWAGGER_UI_PATH = "/swagger-ui/**";

    public static final String SWAGGER_V3_API_DOCS = "/v3/api-docs/**";


}

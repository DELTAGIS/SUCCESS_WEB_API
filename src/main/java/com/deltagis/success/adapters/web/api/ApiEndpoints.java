package com.deltagis.success.adapters.web.api;


public class ApiEndpoints {
    public static final String BASE_API = "/api";
    public static final String USERS = BASE_API + "/users";
    public static final String AUTH = BASE_API + "/auth";

    // AUTH ENDPOINTS
    public static final String REGISTER_USER = "/signup";
    public static final String REGISTER_USER_ENDPOINT = AUTH + REGISTER_USER;
    public static final String REGISTER_FIRST_USER = "/signup-first-user";
    public static final String REGISTER_FIRST_USER_ENDPOINT = AUTH + REGISTER_FIRST_USER;
    public static final String LOGIN_USER = "/signin";
    public static final String LOGIN_USER_ENDPOINT = AUTH + LOGIN_USER;

    // SWAGGER ENDPOINTS
    public static final String SWAGGER_UI = "/swagger-ui.html";
    public static final String SWAGGER_UI_PATH = "/swagger-ui/**";
    public static final String SWAGGER_V3_API_DOCS = "/v3/api-docs/**";

    // USERS ENDPOINTS
}

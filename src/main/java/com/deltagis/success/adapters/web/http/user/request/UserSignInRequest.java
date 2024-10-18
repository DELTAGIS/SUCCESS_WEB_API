package com.deltagis.success.adapters.web.http.user.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignInRequest {
    private String username;

    private String password;
}

package com.deltagis.success.application.services.user.auth;

import com.deltagis.success.domain.entities.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse {
    AuthenticationRecord auth;
    User user;

    public AuthenticationResponse(AuthenticationRecord auth, User user) {
        this.auth = auth;
        this.user = user;
    }
}

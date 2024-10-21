package com.deltagis.success.application.services.user.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthenticationRecord(@JsonProperty String token, @JsonProperty String type) {

}

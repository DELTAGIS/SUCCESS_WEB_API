package com.deltagis.success.application.services.user.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthenticationResponse(@JsonProperty String token, @JsonProperty String type) {

}

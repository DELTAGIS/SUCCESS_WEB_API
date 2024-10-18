package com.deltagis.success.adapters.web.http;

import com.deltagis.success.adapters.web.api.ApiEndpoints;
import com.deltagis.success.adapters.web.api.ApiResponseService;
import com.deltagis.success.domain.ports.user.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.USERS)
@Tag(name = "User API", description = "Operations related to User management")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ApiResponseService response;

    public Class<?> className() {
        return this.getClass();
    }
}

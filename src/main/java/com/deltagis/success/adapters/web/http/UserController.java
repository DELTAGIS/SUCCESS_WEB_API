package com.deltagis.success.adapters.web.http;


import com.deltagis.success.domain.entities.user.User;
import com.deltagis.success.domain.service.user.UserService;
import com.deltagis.success.adapters.web.api.ApiResponse;
import com.deltagis.success.adapters.web.api.ApiResponseService;
import com.deltagis.success.adapters.web.api.ApiEndpoints;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.USERS)
@Tag(name = "User API", description = "Operations related to User management")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApiResponseService response;

    public Class<?> className() {
        return this.getClass();
    }

    @PostMapping(ApiEndpoints.REGISTER_USER)
    @Operation(summary = "Register a new user", description = "Creates a new user account and returns the created user")
    public ResponseEntity<ApiResponse<User>> registerUser(@RequestBody User user) {
        try {
            User savedUser = userService.saveUser(user);
            // authLogger.logRegister(user.getUsername());  // Log registration

            return response.success("User registered successfully", savedUser, HttpStatus.CREATED, className());
        } catch (Exception e) {
            return response.error("Error registering user", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, className());
        }
    }
}

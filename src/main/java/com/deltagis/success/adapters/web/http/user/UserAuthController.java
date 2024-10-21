package com.deltagis.success.adapters.web.http.user;

import com.deltagis.success.adapters.web.api.ApiEndpoints;
import com.deltagis.success.adapters.web.api.ApiResponse;
import com.deltagis.success.adapters.web.api.ApiResponseService;
import com.deltagis.success.adapters.web.http.user.request.UserSignInRequest;
import com.deltagis.success.adapters.web.message.user.UserCustomMessage;
import com.deltagis.success.application.services.user.auth.AuthenticationRecord;
import com.deltagis.success.application.services.user.auth.AuthenticationResponse;
import com.deltagis.success.domain.entities.user.User;
import com.deltagis.success.domain.ports.user.IUserService;
import com.deltagis.success.domain.ports.user.auth.IUserAuthService;
import com.deltagis.success.infrastructure.config.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiEndpoints.AUTH)
@Tag(name = "User API", description = "Operations related to User Authentication")
public class UserAuthController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IUserAuthService userAuthService;

    private final AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;
    public UserAuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;

    }

    @Autowired
    private ApiResponseService response;

    public Class<?> className() {
        return this.getClass();
    }

    /**
     * Authenticates a user with the provided username and password.
     * If authentication is successful, returns a response with the authentication token and user details.
     * If authentication fails, returns an error response indicating invalid credentials.
     * Handles exceptions and returns an internal server error response in case of unexpected errors.
     *
     * @param user the UserSignInRequest containing the username and password
     * @return a ResponseEntity containing an ApiResponse with the authentication token and user details, or an error message
     */
    @PostMapping(ApiEndpoints.LOGIN_USER)
    @Operation(summary = "Login a user", description = "Logs in a user and returns the authentication token")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> login(@RequestBody UserSignInRequest user) {
        try {
            AuthenticationResponse auth = new AuthenticationResponse(null, null);
            AuthenticationRecord record = new AuthenticationRecord(userAuthService.authenticate(user.getUsername(), user.getPassword()), "Bearer");
            User userAttempt = null;

            if (record.token() != null) {
                userAttempt = userService.getUserByUsername(user.getUsername());
                auth.setUser(userAttempt);
                auth.setAuth(record);

                return response.success(
                        "User registered successfully",
                        auth,
                        HttpStatus.OK,
                        className()
                );
            }

            return response.error(
                    UserCustomMessage.INVALID_CREDENTIALS.getMessage(),
                    UserCustomMessage.INVALID_CREDENTIALS.getMessage(),
                    HttpStatus.UNAUTHORIZED,
                    className()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return response.error(
                    "Error registering user",
                    UserCustomMessage.INTERNAL_ERROR.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR, className()
            );
        }
    }

    @PostMapping(ApiEndpoints.REGISTER_USER)
    @Operation(summary = "Register a new user", description = "Creates a new user account and returns the created user")
    public ResponseEntity<ApiResponse<User>> registerUser(@RequestBody User user) {
        try {
            User savedUser = userAuthService.registerUser(user);
            return response.success("User registered successfully", savedUser, HttpStatus.CREATED, className());
        } catch (Exception e) {
            return response.error("Error registering user", e.getMessage(), HttpStatus.UNAUTHORIZED, className());
        }
    }

    @PostMapping(ApiEndpoints.REGISTER_FIRST_USER)
    @Operation(summary = "If don't have any user, register first user", description = "Create a new user account and returns the created user")
    public ResponseEntity<ApiResponse<User>> registerFirstUser(@RequestBody User user, @RequestParam String secretKey) {
        try {
            User savedUser = userAuthService.registerFirstUser(user, secretKey);
            if (savedUser != null) {
                return response.success("User registered successfully", savedUser, HttpStatus.CREATED, className());
            }

            return response.error("Error registering user", "Invalid secret key", HttpStatus.BAD_REQUEST, className());
        } catch (Exception e) {
            return response.error("Error registering user", e.getMessage(), HttpStatus.UNAUTHORIZED, className());
        }
    }
}

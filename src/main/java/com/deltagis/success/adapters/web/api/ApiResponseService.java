package com.deltagis.success.adapters.web.api;

import com.deltagis.success.domain.service.logs.RequestLoggingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ApiResponseService {

    private static final Logger logger = LoggerFactory.getLogger(ApiResponseService.class);

    private final Environment env;
    // private final RequestLoggingService requestLoggingService;

    public ApiResponseService(Environment env, RequestLoggingService requestLoggingService) {
        this.env = env;
        // this.requestLoggingService = requestLoggingService;
    }

    /**
     * Generates a successful {@link ApiResponse} with the given message, data, and HTTP status.
     *
     * @param message         The message to include in the response.
     * @param data            The data to include in the response.
     * @param status          The HTTP status code to use for the response.
     * @param controllerClass The class of the controller making the request.
     * @return A ResponseEntity containing the ApiResponse.
     */
    public <T> ResponseEntity<ApiResponse<T>> success(String message, T data, HttpStatus status, Class<?> controllerClass) {
        if (Boolean.parseBoolean(env.getProperty("custom.logging.enabled"))) {
            logger.info("Success Response from {}: status={}, message={}, data={}", controllerClass.getSimpleName(), status, message, data);
        }

        ApiResponse<T> response = new ApiResponse<>(
                status.value(),
                message,
                data,
                null,
                true
        );

        // ? Log request using the requestLoggingService
        // requestLoggingService.logRequest(request);

        return new ResponseEntity<>(response, status);
    }

    public <T> ResponseEntity<ApiResponse<T>> success(String message, HttpStatus status, Class<?> controllerClass) {
        return success(message, null, status, controllerClass);
    }

    public <T> ResponseEntity<ApiResponse<T>> error(String message, String error, HttpStatus status, Class<?> controllerClass) {
        if (Boolean.parseBoolean(env.getProperty("custom.logging.enabled"))) {
            logger.error("Error Response from {}: status={}, message={}, error={}", controllerClass.getSimpleName(), status, message, error);
        }

        ApiResponse<T> response = new ApiResponse<>(
                status.value(),
                message,
                null,
                error,
                false
        );

        // requestLoggingService.logRequest();

        return new ResponseEntity<>(response, status);
    }
}

package com.deltagis.success.adapters.web.exceptio;

import com.deltagis.success.adapters.web.api.ApiResponse;
import com.deltagis.success.adapters.web.api.ApiResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final ApiResponseService apiResponseService;

    public GlobalExceptionHandler(ApiResponseService apiResponseService) {
        this.apiResponseService = apiResponseService;
    }

    /**
     * Handles the NoHandlerFoundException and returns a response with an error message.
     *
     * @param ex The NoHandlerFoundException that was thrown
     * @return A ResponseEntity containing the error response
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFound(NoHandlerFoundException ex) {
        String message = "The requested URL was not found on this server.";
        return apiResponseService.error(message, ex.getMessage(), HttpStatus.NOT_FOUND, GlobalExceptionHandler.class);
    }
}

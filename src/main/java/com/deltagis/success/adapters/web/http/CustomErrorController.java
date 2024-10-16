package com.deltagis.success.adapters.web.http;

import com.deltagis.success.adapters.web.api.ApiResponseService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(CustomErrorController.class);
    private final ApiResponseService apiResponseService;

    public CustomErrorController(ApiResponseService apiResponseService) {
        this.apiResponseService = apiResponseService;
    }

    @RequestMapping("/error")
    public ResponseEntity<?> handleError(HttpServletRequest request) {
        Object status = request.getAttribute("javax.servlet.error.status_code");
        HttpStatus httpStatus = HttpStatus.resolve((Integer) status);

        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        String errorMessage = "An error occurred while accessing: " + requestUri;

        logger.error("Error occurred: status={}, uri={}", status, requestUri);

        if (httpStatus == HttpStatus.NOT_FOUND) {
            return apiResponseService.error(
                    "Endpoint not found: " + requestUri,
                    "No handler found for request.",
                    HttpStatus.NOT_FOUND,
                    CustomErrorController.class
            );
        } else if (httpStatus == HttpStatus.INTERNAL_SERVER_ERROR) {
            return apiResponseService.error(
                    "Internal server error",
                    "An unexpected error occurred.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    CustomErrorController.class
            );
        }

        return apiResponseService.error(
                "An error occurred",
                "An unexpected error occurred.",
                HttpStatus.valueOf((Integer) status),
                CustomErrorController.class
        );
    }

}

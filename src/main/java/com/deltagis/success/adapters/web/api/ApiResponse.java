package com.deltagis.success.adapters.web.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse<T> {

    private int status;
    private String message;
    private T data;
    private String errors;
    private boolean success;

    public ApiResponse() {
    }
}

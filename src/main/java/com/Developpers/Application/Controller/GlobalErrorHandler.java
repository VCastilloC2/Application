package com.Developpers.Application.Controller;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalErrorHandler {

    public String handleCustom(int code) {
        return switch (code) {
            case 400 -> "Error/400";
            case 401 -> "Error/401";
            case 403 -> "Error/403";
            case 404 -> "Error/404";
            case 500 -> "Error/500";
            default -> "Error/Error";
        };
    }

}
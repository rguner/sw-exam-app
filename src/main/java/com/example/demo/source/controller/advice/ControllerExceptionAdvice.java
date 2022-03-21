package com.example.demo.source.controller.advice;

import com.example.demo.source.exception.ElementNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.interfaces.ECKey;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(value = ElementNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Map<String, Object> exception(ElementNotFoundException elementNotFoundException) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", elementNotFoundException.getMessage());
        return body;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, Object> onMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            if (sb.length() > 0) {
                sb.append(" | ");
            }
            sb.append(fieldError.getField());
            sb.append(" - ");
            sb.append(fieldError.getDefaultMessage());
        }
        body.put("message", sb.toString());
        return body;
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String, Object> exception(RuntimeException runtimeException) {
        // log required..
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", runtimeException.getMessage());
        return body;
    }
}
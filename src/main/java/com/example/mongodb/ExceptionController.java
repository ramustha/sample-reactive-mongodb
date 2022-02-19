package com.example.mongodb;


import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(WebExchangeBindException.class)
  public Map<String, Object> handleValidationExceptions(WebExchangeBindException ex) {
    Map<String, Object> response = new LinkedHashMap<>();
    Map<String, String> errors = new HashMap<>();

    response.put("code", ex.getStatus().value());
    response.put("status", ex.getStatus().name());

    ex.getBindingResult().getAllErrors().forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
      response.put("errors", errors);
    });

    return response;
  }
}

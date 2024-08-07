/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseDto {
    private int rc;
    private String message;
    private HttpStatus httpStatus;
    private List<FieldError> errors;

    private ErrorResponseDto() {
    }

    public ErrorResponseDto(HttpStatus httpStatus, String message) {
        this(httpStatus, message, (List<FieldError>) null);
    }

    public ErrorResponseDto(HttpStatus httpStatus, String message, BindingResult bindingResult) {
        this(httpStatus, message, FieldError.of(bindingResult));
    }

    public ErrorResponseDto(HttpStatus httpStatus, String message, List<FieldError> errors) {
        this();
        this.httpStatus = httpStatus;
        this.message = message;
        this.rc = httpStatus.value();
//        this.path = path;
        this.errors = errors;
    }

    @Getter
    public static class FieldError {
        private final String field;
        private final String value;
        private final String reason;

        private FieldError(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static List<FieldError> of(String field, String value, String reason) {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(field, value, reason));
            return fieldErrors;
        }

        private static List<FieldError> of(BindingResult bindingResult) {
            List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }
}

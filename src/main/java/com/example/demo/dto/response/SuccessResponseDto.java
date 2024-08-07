/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class SuccessResponseDto<T> {
    public static final String SUCCESS_MSG = "Success";
    private int rc;
    private String message;
    private HttpStatus httpStatus;
    private T data;

    public SuccessResponseDto(HttpStatus httpStatus, String status, T data) {
        this.rc = httpStatus.value();
        this.httpStatus = httpStatus;
        this.message = status;
        this.data = data;
    }

    public SuccessResponseDto(HttpStatus httpStatus, T data) {
        this(httpStatus, SUCCESS_MSG, data);
    }

    public SuccessResponseDto(HttpStatus httpStatus) {
        this(httpStatus, SUCCESS_MSG, null);
    }


    public SuccessResponseDto() {

    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class RequestDto {

    @NotBlank(message = "Name may not be blank.")
    private String name;
    @NotBlank(message = "secondname may not be blank.")
    private String secondname;
    @NotBlank(message = "work may not be blank.")
    private String work;
    @Email(message = "Please enter a valid email.")
    @NotBlank(message = "Email may not be blank.")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",
            message = "Please add valid email address")
    private String email;
    @NotBlank(message = "Phone number may not be blank.")
    @Size(min = 7, max = 15, message = "Please enter a valid phone number.")
    @Pattern(regexp = "^[0-9]*$", message = "Only numbers in phone number")
    private String phone;
    private String description;

}

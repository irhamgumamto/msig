package com.example.demo.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class SearchDto {
    @NotBlank(message = "startDate may not be blank.")
    private String startDate;
    @NotBlank(message = "endDate may not be blank.")
    private String endDate;

}

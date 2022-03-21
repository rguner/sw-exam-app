package com.example.demo.source.dto.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class CustomerRequestDto {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Min(0)
    @Max(120)
    private Integer age;
}

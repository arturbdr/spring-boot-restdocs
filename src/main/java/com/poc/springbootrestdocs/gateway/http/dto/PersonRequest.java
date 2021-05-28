package com.poc.springbootrestdocs.gateway.http.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonRequest {

    @NotBlank(message = "The name is required!")
    private String name;

    @DecimalMin(value = "0")
    @DecimalMax(value = "150")
    private Integer age;

}

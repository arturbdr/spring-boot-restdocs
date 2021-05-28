package com.poc.springbootrestdocs.gateway.http.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Person {
    String id;
    String name;
    Integer age;
}

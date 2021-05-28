package com.poc.springbootrestdocs.gateway.http;

import com.poc.springbootrestdocs.gateway.http.dto.Person;
import com.poc.springbootrestdocs.gateway.http.dto.PersonRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
public class PersonController {

    @PostMapping(value = "/person", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> createPerson(@RequestBody @Valid PersonRequest personRequest) {

        return ResponseEntity.status(CREATED)
                .body(Person.builder()
                        .age(personRequest.getAge())
                        .name(personRequest.getName())
                        .id(UUID.randomUUID().toString())
                        .build());
    }
}

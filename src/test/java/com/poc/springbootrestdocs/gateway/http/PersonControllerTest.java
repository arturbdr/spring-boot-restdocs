package com.poc.springbootrestdocs.gateway.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.springbootrestdocs.gateway.http.dto.PersonRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setupTest() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldCreateANewPerson() throws Exception {

        final PersonRequest givenPerson = PersonRequest.builder()
                .name("Sample Person name")
                .age(20)
                .build();

        this.mockMvc.perform(post("/person")
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(givenPerson)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("\"name\":\"Sample Person name\",\"age\":20")))
                .andDo(document("home"));;
    }

}
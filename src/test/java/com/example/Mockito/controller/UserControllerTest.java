package com.example.Mockito.controller;

import com.example.Mockito.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.catalina.mapper.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
@WebAppConfiguration // Aqui indicamos que vamos a levantar el servidor completo pero, con una instancia de Test
class UserControllerTest {

    private final static String BASE_URL = "/api/v1/user";
    MockMvc mockMvc;
    @Autowired

    private WebApplicationContext webApplicationContext;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void postUser() throws Exception {
        String path = "/create";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL+path)
                .accept(MediaType.APPLICATION_JSON_VALUE) // Tipo de contenido que puede aceptar
                .contentType(MediaType.APPLICATION_JSON_VALUE) // Tipo de contenido que le vamos a pasar
                .content(mapperToObjet(userBuild())) // Contenido
        ).andReturn();

        Assertions.assertEquals(201,mvcResult.getResponse().getStatus());
    }

    @Test
    void postUserFail() throws Exception {
        String path = "/create";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL+path)
                .accept(MediaType.APPLICATION_JSON_VALUE) // Tipo de contenido que puede aceptar
                .contentType(MediaType.APPLICATION_JSON_VALUE) // Tipo de contenido que le vamos a pasar
                .content(mapperToObjet("")) // Contenido
        ).andReturn();

        Assertions.assertEquals(400,mvcResult.getResponse().getStatus());
    }

    @Test
    void findAllUsers() throws Exception {
        String path = "/find/all";
        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL+path)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .queryParam("name","value")
        ).andReturn();
        Assertions.assertEquals(200,mockMvcResult.getResponse().getStatus());
    }

    @Test
    void findUserByEmail() throws Exception {
        String path = "/find/email";

        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL+path)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .queryParam("email","samuelhz1998@gmail.com")
        ).andReturn();

        Assertions.assertEquals(200,mockMvcResult.getResponse().getStatus());
    }

    private User userBuild(){
        return User.builder()
                .id(1L)
                .name("Samuel Jimenez")
                .email("samuelhz1998@gmail.com")
                .dateOfBirth(LocalDate.of(1998,2,11))
                .registerDate(LocalDateTime.now())
                .build();
    }

    public String mapperToObjet(Object entity) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(entity);
    }
}
package com.philippeslater.portfolio.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAll_retourneListe() throws Exception {
        mockMvc.perform(get("/api/projects"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.length()").value(org.hamcrest.Matchers.greaterThan(0)));
    }

    @Test
    void getAll_filtreParCategorie() throws Exception {
        mockMvc.perform(get("/api/projects?category=fullstack"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[*].category").value(
                org.hamcrest.Matchers.everyItem(org.hamcrest.Matchers.is("fullstack"))));
    }

    @Test
    void getAll_filtreFeatures() throws Exception {
        mockMvc.perform(get("/api/projects?featured=true"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[*].featured").value(
                org.hamcrest.Matchers.everyItem(org.hamcrest.Matchers.is(true))));
    }

    @Test
    void getById_retourneProjet() throws Exception {
        mockMvc.perform(get("/api/projects/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.title").isString());
    }

    @Test
    void getById_idInexistant_retourne404() throws Exception {
        mockMvc.perform(get("/api/projects/9999"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.error").isString());
    }
}

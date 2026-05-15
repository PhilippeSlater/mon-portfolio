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
class SkillControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAll_retourneListe() throws Exception {
        mockMvc.perform(get("/api/skills"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.length()").value(org.hamcrest.Matchers.greaterThan(0)));
    }

    @Test
    void getAll_filtreParCategorie() throws Exception {
        mockMvc.perform(get("/api/skills?category=language"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[*].category").value(
                org.hamcrest.Matchers.everyItem(org.hamcrest.Matchers.is("language"))));
    }

    @Test
    void getCategories_retourneListe() throws Exception {
        mockMvc.perform(get("/api/skills/categories"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0]").isString());
    }
}

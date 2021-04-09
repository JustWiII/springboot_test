package com.example.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void unitTest1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api?post_input_text=testing1")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.post("/delete?post_text=testing1")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.get("/history").contentType(MediaType.ALL))
                .andExpect(content().string(not(containsString("testing1"))));
    }

    @Test
    void unitTest2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api?post_input_text=testing2")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.post("/delete?post_text=Testing2")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.post("/delete?post_text=Testing2").contentType(MediaType.ALL))
                .andExpect(content().string(containsString("does not exist")));
    }

}
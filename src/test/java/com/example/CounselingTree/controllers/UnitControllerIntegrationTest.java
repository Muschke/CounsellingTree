package com.example.CounselingTree.controllers;

import com.example.CounselingTree.entities.Unit;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class UnitControllerIntegrationTest {
    private final static String URI = "/units/";
    private ObjectMapper objectMapper;
    private final MockMvc mockMvc;

    public UnitControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    void getAllUnitsThrowsExceptionWhenEmpty() throws Exception{
        String error = mockMvc.perform(get(URI+"showAll"))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType("application/json"))
                .andReturn().getResolvedException().getMessage();

        assertThat(error).isEqualTo("No units present");
    }

    @Test
    void getAllUnitsWorksWhenUnitsPresent() throws Exception{
        mockMvc.perform(post(URI+"addUnit")
                .contentType(MediaType.APPLICATION_JSON)
                .content("new unit"));

        MockHttpServletResponse response = mockMvc.perform(get(URI+"showAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn().getResponse();

        Unit[] allUnits = objectMapper.readValue(response.getContentAsString(), Unit[].class);

        assertThat(allUnits).hasSize(1);
        assertThat(Arrays.stream(allUnits).findFirst().get().getName()).isEqualTo("new unit");
        assertThat(Arrays.stream(allUnits).findAny().get().getName()).isEqualTo("new unit");
    }

    @Test
    void addingValidUnitWorks() throws Exception{
        mockMvc.perform(post(URI+"addUnit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("newUnit"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("New unit added succesfully"));
    }

    @Test
    void addNewUnitWithInvalidUnitNameThrowsError() throws Exception{
        String error = mockMvc.perform(post(URI+"addUnit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(" "))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andReturn().getResolvedException().getMessage();

        assertThat(error).isEqualTo("Unitname cannot be blank or empty");
    }

    @Test
    void addingNewUnitWithAlreadyExistingUnitNameThrowsError() throws Exception{
        mockMvc.perform(post(URI+"addUnit")
                .contentType(MediaType.APPLICATION_JSON)
                .content("new unit"));

        String error = mockMvc.perform(post(URI+"addUnit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("new unit"))
                .andExpect(status().isConflict())
                .andExpect(content().contentType("application/json"))
                .andReturn().getResolvedException().getMessage();

        assertThat(error).isEqualTo("Unit already exists");
    }


}

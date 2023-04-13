package com.example.CounselingTree.controllers;

import com.example.CounselingTree.entities.EnumerationLevel;
import com.example.CounselingTree.entities.Unit;
import com.example.CounselingTree.payload.Level;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EnumerationLevelControllerIntegrationTest {
    private final static String URI = "/levels/";
    private ObjectMapper objectMapper;
    private final MockMvc mockMvc;

    public EnumerationLevelControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    void getAllLevelsThrowsExceptionWhenEmpty() throws Exception{
        String error = mockMvc.perform(get(URI+"showAll"))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType("application/json"))
                .andReturn().getResolvedException().getMessage();
        

        assertThat(error).isEqualTo("No enumerationlevels present");
    }

    @Test
    void getAllLevelsWorksWhenLevelsPresent() throws Exception{
        EnumerationLevel mocklevel = new EnumerationLevel("A1", "Mockdescripition");

        mockMvc.perform(post(URI+"addLevel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mocklevel)));

        MockHttpServletResponse response = mockMvc.perform(get(URI+"showAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn().getResponse();

        EnumerationLevel[] allLevels = objectMapper.readValue(response.getContentAsString(), EnumerationLevel[].class);

        assertThat(allLevels).hasSize(1);
        assertThat(Arrays.stream(allLevels).findFirst().get().getDescription()).isEqualTo("Mockdescripition");
        assertThat(Arrays.stream(allLevels).findAny().get().getDescription()).isEqualTo("Mockdescripition");
        assertThat(Arrays.stream(allLevels).findFirst().get().getCode()).isEqualTo("A1");
        assertThat(Arrays.stream(allLevels).findAny().get().getCode()).isEqualTo("A1");
    }

    @Test
    void addNewLevelWithInvalidDescriptionThrowsError() throws Exception{
        Level mockpayload = new Level("A1", " ");
        String error = mockMvc.perform(post(URI+"addLevel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockpayload)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andReturn().getResolvedException().getMessage();

        assertThat(error).isEqualTo("Description cannot be blank or empty");
    }

    @Test
    void addNewLevelWithInvalidCodeThrowsError() throws Exception{
        Level mockpayload = new Level("Z1", "mockdescription");
        String error = mockMvc.perform(post(URI+"addLevel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockpayload)))
                .andExpect(status().isConflict())
                .andExpect(content().contentType("application/json"))
                .andReturn().getResolvedException().getMessage();

        assertThat(error).isEqualTo("Invalid code, first charachter must be captial A-F, second has to be digit");
    }



    @Test
    void addingNewUnitWithAlreadyExistingCodeNameThrowsError() throws Exception{
        Level mockpayload = new Level("A1", "mockdescription");
        Level mockpayloadTwo = new Level("A1", "mockdescriptionTwo");

        mockMvc.perform(post(URI+"addLevel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockpayload)));

        String error = mockMvc.perform(post(URI+"addLevel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockpayloadTwo)))
                .andExpect(status().isConflict())
                .andExpect(content().contentType("application/json"))
                .andReturn().getResolvedException().getMessage();

        assertThat(error).isEqualTo("Levelcode already exists");
    }

    @Test
    void addingNewUnitWithAlreadyExistingDescriptionNameThrowsError() throws Exception{
        Level mockpayload = new Level("A1", "mockdescription");
        Level mockpayloadTwo = new Level("A2", "mockdescription");

        mockMvc.perform(post(URI+"addLevel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockpayload)));

        String error = mockMvc.perform(post(URI+"addLevel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockpayloadTwo)))
                .andExpect(status().isConflict())
                .andExpect(content().contentType("application/json"))
                .andReturn().getResolvedException().getMessage();

        assertThat(error).isEqualTo("Description already exists");
    }

    @Test
    void addingValidLevelWorks() throws Exception{
        Level mockpayload = new Level("A1", "mockdescription");
        mockMvc.perform(post(URI+"addLevel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockpayload)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("New level added succesfully"));
    }
}

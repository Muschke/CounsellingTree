package com.example.CounselingTree.controllers.integration;

import com.example.CounselingTree.entities.Employee;
import com.example.CounselingTree.entities.EnumerationLevel;
import com.example.CounselingTree.payload.EmployeeDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllorIntegrationTest {

    private final static String URI = "/employees/";
    private ObjectMapper objectMapper;
    private final MockMvc mockMvc;

    public EmployeeControllorIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    void getAllEmployeesThrowsExceptionWhenEmpty() throws Exception{
        String error = mockMvc.perform(get(URI+"showAll"))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType("application/json"))
                .andReturn().getResolvedException().getMessage();

        assertThat(error).isEqualTo("No employees present");
    }
    @Test
    void getAllEmployeesWorksWhenEmployeesPresent() throws Exception{
        this.insertUnitAndLevel();
        EmployeeDto employeeDto = new EmployeeDto("employeeName", "employeeNameSurname", LocalDate.of(2000,04,23),1L,1L);


        mockMvc.perform(post(URI+"addEmployee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employeeDto)));

        MockHttpServletResponse response = mockMvc.perform(get(URI+"showAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn().getResponse();

        Employee[] allLevels = objectMapper.readValue(response.getContentAsString(), Employee[].class);

        assertThat(allLevels).hasSize(1);
        assertThat(Arrays.stream(allLevels).findFirst().get().getName()).isEqualTo("employeeName");
        assertThat(Arrays.stream(allLevels).findAny().get().getName()).isEqualTo("employeeName");
        assertThat(Arrays.stream(allLevels).findFirst().get().getStartDateContract()).isEqualTo(LocalDate.now());
        assertThat(Arrays.stream(allLevels).findAny().get().getStartDateContract()).isEqualTo(LocalDate.now());
    }


    @Test
    void addNewEmployeeWithInvalidNameThrowsError() throws Exception{
        this.insertUnitAndLevel();
        EmployeeDto employeeDto = new EmployeeDto(" ", "employeeNameSurname", LocalDate.of(2000,04,23),1L,1L);
        String error = mockMvc.perform(post(URI+"addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andReturn().getResolvedException().getMessage();

        assertThat(error).isEqualTo("Cannot be empty or blank");
    }
    @Test
    void addNewEmployeeWithInvalidSurnameThrowsError() throws Exception{
        this.insertUnitAndLevel();
        EmployeeDto employeeDto = new EmployeeDto("employeeName", " ", LocalDate.of(2000,04,23),1L,1L);
        String error = mockMvc.perform(post(URI+"addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andReturn().getResolvedException().getMessage();

        assertThat(error).isEqualTo("Cannot be empty or blank");
    }




    @Test
    void addingNewEmployeeWithAlreadyExistingNameSurnameAndBirthdateThrowsError() throws Exception{
        this.insertUnitAndLevel();
        EmployeeDto employeeDto = new EmployeeDto("employeeName", "employeeSurname", LocalDate.of(2000,04,23),1L,1L);
        EmployeeDto employeeDtoCopy = new EmployeeDto("employeeName", "employeeSurname", LocalDate.of(2000,04,23),1L,1L);


        mockMvc.perform(post(URI+"addEmployee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employeeDto)));


        String error = mockMvc.perform(post(URI+"addEmployee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employeeDtoCopy)))
                .andExpect(status().isConflict())
                .andExpect(content().contentType("application/json"))
                .andReturn().getResolvedException().getMessage();

        assertThat(error).isEqualTo("employee with that name, surname and date of birth already exists");
    }



    @Test
    void addingValidLevelWorks() throws Exception{
        this.insertUnitAndLevel();
        EmployeeDto employeeDto = new EmployeeDto("employeeName", "employeeSurname", LocalDate.of(2000,04,23),1L,1L);

        mockMvc.perform(post(URI+"addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("New employee added succesfully"));

    }


    private void insertUnitAndLevel() throws Exception{
        EnumerationLevel mocklevel = new EnumerationLevel("A1", "Mockdescripition");

        mockMvc.perform(post("/units/addUnit")
                .contentType(MediaType.APPLICATION_JSON)
                .content("new unit"));

        mockMvc.perform(post("/levels/addLevel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mocklevel)));
    }
}

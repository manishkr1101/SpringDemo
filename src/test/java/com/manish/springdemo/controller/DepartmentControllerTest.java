package com.manish.springdemo.controller;

import com.manish.springdemo.entity.Department;
import com.manish.springdemo.error.DepartmentNotFoundException;
import com.manish.springdemo.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DepartmentService departmentService;
    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                        .name("CSE")
                        .address("Patna")
                        .deptCode("06")
                        .id(1L)
                        .build();

    }

    @Test
    void saveDepartmentTest() throws Exception {
        Department departmentToSave = Department.builder()
                .name("CSE")
                .address("Patna")
                .deptCode("06")
                .build();

        Mockito.when(departmentService.saveDepartment(departmentToSave)).thenReturn(department);
        mockMvc.perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"name\":\"CSE\",\n" +
                        "    \"address\": \"Patna\",\n" +
                        "    \"deptCode\": \"06\"\n" +
                        "}"))
                .andExpect(status().isOk());

    }

    @Test
    void fetchDepartmentByIdTest() throws Exception {
        Mockito.when(departmentService.fetchDepartmentById(1L)).thenReturn(department);

        mockMvc.perform(get("/departments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(department.getId().toString()))
                .andExpect(jsonPath("$.name").value(department.getName()));

    }
}
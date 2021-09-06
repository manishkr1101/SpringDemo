package com.manish.springdemo.service;

import com.manish.springdemo.entity.Department;
import com.manish.springdemo.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department =
                Department.builder()
                        .name("CE")
                        .address("Patna")
                        .deptCode("06")
                        .id(1L)
                        .build();

        Mockito.when(departmentRepository.findByName("CE")).thenReturn(department);
    }

    @Test
    @DisplayName("Custom name for test")
    void fetchDepartmentByNameTest() {
        String departmentName = "CE";
        Department department = departmentService.fetchDepartmentByName(departmentName);
        assertNotNull(department);
        assertEquals(departmentName, department.getName());
    }
}
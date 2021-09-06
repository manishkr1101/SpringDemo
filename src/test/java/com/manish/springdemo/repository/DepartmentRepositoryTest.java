package com.manish.springdemo.repository;

import com.manish.springdemo.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Department department =
                Department.builder()
                        .name("CSE")
                        .address("Patna")
                        .deptCode("06")
                        .build();

        entityManager.persist(department);
    }

    @Test
    void findByIdTest() {
        Department d = departmentRepository.findById(1L).get();
        assertNotNull(d, "dept not found");
        assertEquals(d.getName(), "CSE");
        assertEquals(d.getId(), 1L);
        assertNotNull(d.getDeptCode());
    }
}
package com.manish.springdemo.service;

import com.manish.springdemo.entity.Department;

import java.util.List;

public interface DepartmentService {
    public void saveDepartment(Department department);

    public List<Department> getDepartments();

    public void deleteDepartmentById(Long departmentId);

    public Department fetchDepartmentById(Long departmentId);

    public Department updateDepartment(Long departmentId, Department department);

    public Department fetchDepartmentByName(String departmentName);
}

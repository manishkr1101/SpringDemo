package com.manish.springdemo.service;

import com.manish.springdemo.entity.Department;
import com.manish.springdemo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void saveDepartment(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) {
        return departmentRepository.getById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Department dept = departmentRepository.findById(departmentId).get();
        if(Objects.nonNull(dept.getName()) && !dept.getName().equals("")) {
            dept.setName(department.getName());
        }

        if(Objects.nonNull(department.getDeptCode()) &&
                !"".equalsIgnoreCase(department.getDeptCode())) {
            dept.setDeptCode(department.getDeptCode());
        }

        if(Objects.nonNull(department.getAddress()) &&
                !"".equalsIgnoreCase(department.getAddress())) {
            dept.setAddress(department.getAddress());
        }

        return departmentRepository.save(dept);
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
        return departmentRepository.findByName(departmentName);
    }
}

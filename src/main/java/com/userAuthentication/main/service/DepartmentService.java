package com.userAuthentication.main.service;

import com.userAuthentication.main.model.Department;

import java.util.List;

public interface DepartmentService {
    void saveDepartment(Department department);
    List<Department> getAllDepartments();
    Department getDepartmentById(long id);
    void deleteDepartment(long id);
}

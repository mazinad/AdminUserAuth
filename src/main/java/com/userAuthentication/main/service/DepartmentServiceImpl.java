package com.userAuthentication.main.service;

import com.userAuthentication.main.Repository.DepartmentRepository;
import com.userAuthentication.main.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    private DepartmentRepository departmentRepository;
    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Override
    public void saveDepartment(Department department) {
        this.departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(long id) {
    Optional<Department> optional=departmentRepository.findById(id);
    Department d=null;
    if(optional.isPresent()){
        d=optional.get();
    }else{
        logger.error("We could not find this department with this id"+id);
    }
    return d;
    }

    @Override
    public void deleteDepartment(long id) {
        this.departmentRepository.deleteById(id);
    }
}

package com.userAuthentication.main.service;

import com.userAuthentication.main.Repository.StudentRepository;
import com.userAuthentication.main.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignCoursesImpl implements AssignCourses{
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public void saveAssignedCourses(Student students) {
        studentRepository.save(students);
    }
}

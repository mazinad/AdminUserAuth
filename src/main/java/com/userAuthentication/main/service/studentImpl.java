package com.userAuthentication.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userAuthentication.main.Repository.StudentRepository;
import com.userAuthentication.main.model.Student;


@Service
public class studentImpl implements studentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getStudents() {

        return this.studentRepository.findAll();
    }

    @Override
    public void saveStudents(Student student) {
        this.studentRepository.save(student);

    }

    @Override
    public Student getStudentById(long id) {
        Optional<Student> optional = studentRepository.findById(id);
        Student std = null;
        if (optional.isPresent()) {
            std = optional.get();
        } else {
            throw new RuntimeException("Student Not Found" + id);
        }
        return std;
    }

    @Override
    public void deleteStudentId(long id) {
        this.studentRepository.deleteById(id);

    }


    public List<Student> getByKeyWord(String keyword) {
        return studentRepository.findByKeyword(keyword);

    }

}

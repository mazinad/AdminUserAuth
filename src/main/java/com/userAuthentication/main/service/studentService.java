package com.userAuthentication.main.service;

import java.util.List;

import com.userAuthentication.main.model.Student;




public interface studentService {
	List<Student> getStudents();

	void saveStudents(Student student);

	Student getStudentById(long id);

	void deleteStudentId(long id);
}

package com.userAuthentication.main.service;

import java.util.List;

import com.userAuthentication.main.model.Course;




public interface courseService {
	List<Course> getAllCourses();

	void saveCourses(Course courae);

	Course getCoursesById(long id);

	void deleteCoursesById(long id);
}

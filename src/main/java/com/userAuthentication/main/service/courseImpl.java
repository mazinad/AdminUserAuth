package com.userAuthentication.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userAuthentication.main.Repository.CourseRepository;
import com.userAuthentication.main.model.Course;


@Service
public class  courseImpl implements courseService{

	@Autowired
	private CourseRepository courseRepository;
	@Override
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
		
	}

	@Override
	public void saveCourses(Course courae) {
		this.courseRepository.save(courae);
		
	}

	@Override
	public Course getCoursesById(long id) {
	
		Optional<Course>optional=courseRepository.findById(id);
		Course cr=null;
		if(optional.isPresent()) {
			cr=optional.get();
		}else {
		throw new RuntimeException("This employee is not found"+id);
		}
		return cr;
	}
	
	@Override
	public void deleteCoursesById(long id) {
		
		this.courseRepository.deleteById(id);
	}

}

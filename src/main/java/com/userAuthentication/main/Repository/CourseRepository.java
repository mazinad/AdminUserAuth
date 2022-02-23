package com.userAuthentication.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userAuthentication.main.model.Course;



public interface CourseRepository extends JpaRepository<Course, Long>{

}

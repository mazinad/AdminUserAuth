package com.userAuthentication.main.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.userAuthentication.main.model.Student;



public interface StudentRepository extends JpaRepository<Student, Long>{
	 @Query(value = "select * from student s where s.first_name like %:keyword% or s.last_name like %:keyword%", nativeQuery = true)
	 List<Student>findByKeyword(@Param("keyword") String keyword);
}

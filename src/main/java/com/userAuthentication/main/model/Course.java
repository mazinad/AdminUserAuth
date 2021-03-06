package com.userAuthentication.main.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long course_id;
	private String course_name;
	private int credits;
	@ManyToMany(mappedBy = "courses",fetch = FetchType.EAGER)
	private List<Student>student;
	public Long getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	
}

package com.userAuthentication.main.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long st_id;
private String first_name;
private String last_name;
private String location;
@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
@JoinTable(
		name = "student_course",
		joinColumns = @JoinColumn(
	            name = "student_id", referencedColumnName = "st_id"),
		inverseJoinColumns = @JoinColumn(
			            name = "courses_id", referencedColumnName = "course_id"))

private Collection<Course> courses;
public Long getSt_id() {
	return st_id;
}
public void setSt_id(Long st_id) {
	this.st_id = st_id;
}
public String getFirst_name() {
	return first_name;
}
public void setFirst_name(String first_name) {
	this.first_name = first_name;
}
public String getLast_name() {
	return last_name;
}
public void setLast_name(String last_name) {
	this.last_name = last_name;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public Collection<Course> getCourses() {
	return courses;
}
public void setCourses(Collection<Course> courses) {
	this.courses = courses;
}

}

package com.userAuthentication.main.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dept_id;
    private String name;
    @OneToMany(mappedBy="department", fetch= FetchType.LAZY,cascade= CascadeType.ALL)
    @JsonBackReference
    private List<Student> student;

    public Department() {
    }

    public Long getDept_id() {
        return dept_id;
    }

    public void setDept_id(Long dept_id) {
        this.dept_id = dept_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }
}

package com.userAuthentication.main.model;

import javax.persistence.*;
import java.util.Collection;

@Entity()
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy="department", fetch= FetchType.LAZY,cascade= CascadeType.ALL)
    private Collection<Student> student;

    public Department() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Student> getStudent() {
        return student;
    }

    public void setStudent(Collection<Student> student) {
        this.student = student;
    }
}

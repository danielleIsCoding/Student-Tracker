package com.launchcode.HoursTrackingApp.domain;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Student {

    private Integer id;
    private String name;
    private User user;
    private Set<Subject> subjects = new TreeSet<>();

    public Student (){}

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        this.name = name;
        return name;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "students")
    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

}

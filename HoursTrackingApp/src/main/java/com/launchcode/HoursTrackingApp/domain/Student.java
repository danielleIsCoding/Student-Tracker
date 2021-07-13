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

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "student")
    public Set<Subject> getSubject() {
        return subjects;
    }

    public void setSubject(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}

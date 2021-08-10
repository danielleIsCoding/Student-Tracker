package com.launchcode.HoursTrackingApp.domain;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;
@Entity
public class Subject {

    private Integer id;
    private String name;
    private Student students;
    private Double totalHours;
    private Set<Hours> hours = new TreeSet<>();

    public Subject(String name, Student students, Double totalHours, Set<Hours> hours) {
        this.name = name;
        this.students = students;
        this.totalHours = totalHours;
        this.hours = hours;
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Subject(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subjects" )
    public Set<Hours> getHours() {
        return hours;
    }

    public void setHours(Set<Hours> hours) {
        this.hours = hours;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    public Student getStudents() {
        return students;
    }

    public void setStudents(Student students) {
        this.students = students;
    }

    public Double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Double totalHours) {
        this.totalHours = totalHours;
    }
}

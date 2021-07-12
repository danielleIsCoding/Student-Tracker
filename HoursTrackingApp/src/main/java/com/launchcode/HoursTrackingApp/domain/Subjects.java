package com.launchcode.HoursTrackingApp.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Subjects {

    private Integer id;
    private String name;
    private Date startDate;
    private Date endDate;
    private Student student;
    private Set<Hours> hours = new TreeSet<>();

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Subjects(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @Column(name = "start_date")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

//    @Column(name = "end_date")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @ManyToOne
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subjects" )
    public Set<Hours> getHours() {
        return hours;
    }

    public void setHours(Set<Hours> hours) {
        this.hours = hours;
    }
}

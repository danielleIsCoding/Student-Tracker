package com.launchcode.HoursTrackingApp.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Hours {

    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Float total;
    private String notes;
    private Subject subjects;

    public Hours (){}

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Subject getSubjects() {
        return subjects;
    }

    public void setSubjects(Subject subjects) {
        this.subjects = subjects;
    }

}

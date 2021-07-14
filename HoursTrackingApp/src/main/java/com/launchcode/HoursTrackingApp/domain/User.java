package com.launchcode.HoursTrackingApp.domain;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name="users")
public class User  {

    private Integer id;
    private String Username;
    private String password;
    private String confirmPassword;
    private Set<Authority> authorities = new HashSet<>();


    private Set<Student> student = new TreeSet<>();

    public User(){}

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "users")
    public Set<Student> getStudent() {
        return student;
    }

    public void setStudent(Set<Student> student) {
        this.student = student;
    }

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="users")
    public Set<Authority> getAuthorities()
    {
        return authorities;
    }
    public void setAuthorities(Set<Authority> authorities)
    {
        this.authorities = authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getConfirmPassword()
    {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword)
    {
        this.confirmPassword = confirmPassword;
    }

}

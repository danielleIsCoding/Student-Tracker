package com.launchcode.HoursTrackingApp.domain;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name="users")
public class User  {

    private Integer id;
    private String Username;
    private String pwHash;
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    private Set<Student> student = new TreeSet<>();

    public User(){}

    public User(String Username, String password) {
        this.Username = Username;
        this.pwHash = encoder.encode(password);;
    }

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

    public String getPassword() {
        return pwHash;
    }

    public void setPassword(String password) {
        this.pwHash = password;
    }

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "user")
    public Set<Student> getStudent() {
        return student;
    }

    public void setStudent(Set<Student> student) {
        this.student = student;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

}

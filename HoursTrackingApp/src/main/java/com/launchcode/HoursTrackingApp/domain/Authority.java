package com.launchcode.HoursTrackingApp.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Authority implements GrantedAuthority {
    private Integer id;
    private String authority;
    private User users;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @ManyToOne
    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}

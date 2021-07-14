package com.launchcode.HoursTrackingApp.security;

import com.launchcode.HoursTrackingApp.domain.Authority;
import com.launchcode.HoursTrackingApp.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

public class SecurityUser extends User implements UserDetails {

    public SecurityUser(User user){
        this.setAuthorities(user.getAuthorities());
        this.setStudent(user.getStudent());
        this.setId(user.getId());
        this.setPassword(user.getPassword());
        this.setUsername(user.getUsername());
    }


    @Override
    public Set<Authority> getAuthorities() {
        return super.getAuthorities();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

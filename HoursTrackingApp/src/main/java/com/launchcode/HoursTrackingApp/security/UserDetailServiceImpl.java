package com.launchcode.HoursTrackingApp.security;

import com.launchcode.HoursTrackingApp.domain.User;
import com.launchcode.HoursTrackingApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (username == null)
            throw new UsernameNotFoundException("User does not exist!");

        return new SecurityUser(user);

    }
}

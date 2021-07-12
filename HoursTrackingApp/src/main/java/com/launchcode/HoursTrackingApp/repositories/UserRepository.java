package com.launchcode.HoursTrackingApp.repositories;

import com.launchcode.HoursTrackingApp.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String Username);
}

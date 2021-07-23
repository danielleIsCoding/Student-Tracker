package com.launchcode.HoursTrackingApp.repositories;

import com.launchcode.HoursTrackingApp.domain.Student;
import com.launchcode.HoursTrackingApp.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;



@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

    TreeSet<Student> findByUserIdIn(Set<Optional<User>> users);
}

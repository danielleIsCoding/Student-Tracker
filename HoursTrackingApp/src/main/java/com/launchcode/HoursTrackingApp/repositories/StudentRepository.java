package com.launchcode.HoursTrackingApp.repositories;

import com.launchcode.HoursTrackingApp.domain.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {


}

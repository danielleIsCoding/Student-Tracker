package com.launchcode.HoursTrackingApp.repositories;

import com.launchcode.HoursTrackingApp.domain.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Integer> {
}

package com.riwi.demo.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.demo.domain.entity.Enrollments;

@Repository
public interface enrollmentsRepository extends JpaRepository<Enrollments, String>{
    
}

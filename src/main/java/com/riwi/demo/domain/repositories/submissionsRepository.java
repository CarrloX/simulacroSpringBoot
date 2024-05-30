package com.riwi.demo.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.demo.domain.entity.submissions;

@Repository
public interface submissionsRepository extends JpaRepository<submissions, String>{
    
}

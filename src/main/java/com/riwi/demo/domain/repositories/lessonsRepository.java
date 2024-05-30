package com.riwi.demo.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.demo.domain.entity.lessons;

@Repository
public interface lessonsRepository extends JpaRepository<lessons, String>{
    
}

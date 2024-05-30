package com.riwi.demo.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.demo.domain.entity.Messages;

@Repository
public interface messagesRepository extends JpaRepository<Messages, String>{
    
}

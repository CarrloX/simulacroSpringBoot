package com.riwi.demo.domain.entity;

import com.riwi.demo.utils.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String user_id;
    @Column(nullable = false, unique = true, length = 50)
    private String username;
    @Column(nullable = false, length = 255)
    private String password;
    @Column(length = 100)
    private String email;
    @Column(length = 100,nullable = false)
    private String full_name;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
}

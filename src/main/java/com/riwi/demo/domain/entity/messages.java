package com.riwi.demo.domain.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "messages")
public class messages {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String message_id;
    private String message_content;
    private Date send_date;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private courses course_id;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id")
    private users sender_id;

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "user_id")
    private users receiver_id;
}

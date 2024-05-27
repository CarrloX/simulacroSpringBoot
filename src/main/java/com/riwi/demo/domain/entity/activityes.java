package com.riwi.demo.domain.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Entity(name = "activityes")
public class activityes {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String activity_id;
    @Column(nullable = false,length = 100)
    private String activity_title;
    private String description;
    private Date due_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id", referencedColumnName = "lesson_id")
    private lessons lesson_id;
}

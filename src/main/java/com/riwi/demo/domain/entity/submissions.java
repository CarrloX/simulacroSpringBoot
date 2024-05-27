package com.riwi.demo.domain.entity;

import java.math.BigDecimal;
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
@Entity(name = "submissions")
public class submissions {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String submission_id;
    private String content;
    private Date submission_date;
    private BigDecimal grade;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private users user_id;

    @ManyToOne
    @JoinColumn(name = "activity_id",referencedColumnName = "activity_id")
    private activityes activity_id;
}

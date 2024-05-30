package com.riwi.demo.domain.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMax;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "submissions")
public class Submissions {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String submission_id;
    private String content;
    private Date submission_date;
    @DecimalMax("5,2")
    private double grade;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private Users user_id;

    @ManyToOne
    @JoinColumn(name = "activity_id",referencedColumnName = "activity_id")
    private Activityes activity_id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user_id", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Users> users;
}

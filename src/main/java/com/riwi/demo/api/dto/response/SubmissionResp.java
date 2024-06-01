package com.riwi.demo.api.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.riwi.demo.domain.entity.Activityes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionResp {
    private String submission_id;
    private String content;
    private LocalDate submission_date;
    private double grade;
    private BasicUserResp user;
    private Activityes activity;
    private List<BasicUserResp> users;
}

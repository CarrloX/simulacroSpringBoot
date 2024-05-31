package com.riwi.demo.api.dto.response;

import java.time.LocalDate;

import com.riwi.demo.domain.entity.Courses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentsResp {
    private String enrollment_id;
    private LocalDate enrollment_date;
    // private Users user_id;
    private BasicUserResp user;
    private BasicCourseResp course_id;
}

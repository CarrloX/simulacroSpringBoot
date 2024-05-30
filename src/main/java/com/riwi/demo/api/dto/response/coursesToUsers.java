package com.riwi.demo.api.dto.response;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.riwi.demo.domain.entity.Lessons;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class coursesToUsers {
    private String course_id;
    private String course_name;
    private String description;
    private UserInstructorResp instructor_id;
    private List<Lessons> lessons;
}

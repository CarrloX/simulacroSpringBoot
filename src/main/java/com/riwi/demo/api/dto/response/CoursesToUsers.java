package com.riwi.demo.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoursesToUsers {
    private String course_id;
    private String course_name;
    private String description;
    private UserInstructorResp instructor_id;
    // private List<Lessons> lessons;
}

package com.riwi.demo.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoursesResp {
    private String course_id;
    private String course_name;
    private String description;
    // private Users instructor_id;
    private BasicUser user;
    private List<BasicLessonResp> basicLesson;
}

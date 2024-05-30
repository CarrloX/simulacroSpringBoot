package com.riwi.demo.api.dto.response;

import java.util.List;

import com.riwi.demo.domain.entity.Activityes;
import com.riwi.demo.domain.entity.Courses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonResp {
    private String lesson_id;
    private String lesson_title;
    private String content;
    private Courses course_id;
    private List<Activityes> lessons;
}

package com.riwi.demo.api.dto.response;

import java.util.List;

import com.riwi.demo.domain.entity.activityes;
import com.riwi.demo.domain.entity.courses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class lessonResp {
    private String lesson_id;
    private String lesson_title;
    private String content;
    private courses course_id;
    private List<activityes> lessons;
}

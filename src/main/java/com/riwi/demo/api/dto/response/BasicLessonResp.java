package com.riwi.demo.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasicLessonResp {
    private String lesson_id;
    private String lesson_title;
    private String content;
    // private List<Activityes> activityes;
}

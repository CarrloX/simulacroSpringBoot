package com.riwi.demo.api.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasicActivityResp {
    private String activity_id;
    private String activity_title;
    private String description;
    private LocalDate due_date;
    // private BasicLessonResp lesson_id;
}

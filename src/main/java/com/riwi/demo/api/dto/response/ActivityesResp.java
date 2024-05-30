package com.riwi.demo.api.dto.response;

import java.sql.Date;
import java.util.List;

import com.riwi.demo.domain.entity.Lessons;
import com.riwi.demo.domain.entity.Submissions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityesResp {
    private String activity_id;
    private String activity_title;
    private String description;
    private Date due_date;
    private Lessons lesson_id;
    private List<Submissions> submissions;
}

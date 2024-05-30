package com.riwi.demo.api.dto.response;

import java.sql.Date;
import java.util.List;

import com.riwi.demo.domain.entity.lessons;
import com.riwi.demo.domain.entity.submissions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class activityesResp {
    private String activity_id;
    private String activity_title;
    private String description;
    private Date due_date;
    private lessons lesson_id;
    private List<submissions> submissions;
}

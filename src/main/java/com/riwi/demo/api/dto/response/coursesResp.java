package com.riwi.demo.api.dto.response;

import java.util.List;

import com.riwi.demo.domain.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.riwi.demo.domain.entity.Lessons;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class coursesResp {
    private String course_id;
    private String course_name;
    private String description;
    private Users instructor_id;
    private basicUser user;
    private List<Lessons> lessons;
}

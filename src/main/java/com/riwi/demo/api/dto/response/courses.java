package com.riwi.demo.api.dto.response;

import java.util.List;

import com.riwi.demo.domain.entity.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.riwi.demo.domain.entity.lessons;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class courses {
    private String course_id;
    private String course_name;
    private String description;
    private users instructor_id;
    private basicUser user;
    private List<lessons> lessons;
}

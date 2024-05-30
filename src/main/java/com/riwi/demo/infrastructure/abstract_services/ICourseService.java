package com.riwi.demo.infrastructure.abstract_services;

import com.riwi.demo.api.dto.request.CoursesReq;
import com.riwi.demo.api.dto.response.coursesResp;

public interface ICourseService extends CrudService<CoursesReq, coursesResp, String> {
    
}

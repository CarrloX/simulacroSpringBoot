package com.riwi.demo.infrastructure.abstract_services;

import com.riwi.demo.api.dto.request.CoursesReq;
import com.riwi.demo.api.dto.response.CoursesResp;

public interface ICourseService extends CrudService<CoursesReq, CoursesResp, String> {
    
}

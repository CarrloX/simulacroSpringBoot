package com.riwi.demo.infrastructure.abstract_services;

import com.riwi.demo.api.dto.request.coursesReq;
import com.riwi.demo.api.dto.response.coursesResp;

public interface ICourseService extends CrudService<coursesReq, coursesResp, String> {
    
}

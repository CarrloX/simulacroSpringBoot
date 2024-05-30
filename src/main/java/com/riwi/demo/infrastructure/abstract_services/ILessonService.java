package com.riwi.demo.infrastructure.abstract_services;

import com.riwi.demo.api.dto.request.lessonReq;
import com.riwi.demo.api.dto.response.lessonResp;

public interface ILessonService extends CrudService<lessonReq, lessonResp, String> {
    
}

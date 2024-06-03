package com.riwi.demo.infrastructure.abstract_services;

import java.util.List;

import com.riwi.demo.api.dto.request.LessonReq;
import com.riwi.demo.api.dto.response.BasicActivityResp;
import com.riwi.demo.api.dto.response.LessonResp;

public interface ILessonService extends CrudService<LessonReq, LessonResp, String> {
    List<BasicActivityResp> getActivityesByLessonId(String lessonID);
}

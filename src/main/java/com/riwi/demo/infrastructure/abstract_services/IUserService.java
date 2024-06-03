package com.riwi.demo.infrastructure.abstract_services;

import java.util.List;

import com.riwi.demo.api.dto.request.UserReq;
import com.riwi.demo.api.dto.response.BasicCourseResp;
import com.riwi.demo.api.dto.response.UserResp;

public interface IUserService extends CrudService<UserReq, UserResp, String> {
    List<BasicCourseResp> getCoursesByUserId(String userId);
}

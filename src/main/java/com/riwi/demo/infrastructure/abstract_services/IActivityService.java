package com.riwi.demo.infrastructure.abstract_services;

import com.riwi.demo.api.dto.request.ActivityesReq;
import com.riwi.demo.api.dto.response.ActivityesResp;

public interface IActivityService extends CrudService<ActivityesReq, ActivityesResp, String> {

}

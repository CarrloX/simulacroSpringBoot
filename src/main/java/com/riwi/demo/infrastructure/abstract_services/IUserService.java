package com.riwi.demo.infrastructure.abstract_services;

import com.riwi.demo.api.dto.request.userReq;
import com.riwi.demo.api.dto.response.userResp;

public interface IUserService extends CrudService<userReq,userResp, String>{
    
}

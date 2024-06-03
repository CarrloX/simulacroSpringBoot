package com.riwi.demo.infrastructure.abstract_services;

import com.riwi.demo.api.dto.request.MessagesReq;
import com.riwi.demo.api.dto.response.MessagesResp;

public interface IMessagesService extends CrudService<MessagesReq,MessagesResp,String>{
    
}

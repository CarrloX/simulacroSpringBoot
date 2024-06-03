package com.riwi.demo.infrastructure.abstract_services;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.riwi.demo.api.dto.request.MessagesReq;
import com.riwi.demo.api.dto.response.MessagesResp;

public interface IMessagesService extends CrudService<MessagesReq, MessagesResp, String> {

    List<MessagesResp> getMessagesByUsers(@PathVariable String senderId, String receiverId);
}

package com.riwi.demo.infrastructure.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.demo.api.dto.request.MessagesReq;
import com.riwi.demo.api.dto.response.BasicCourseResp;
import com.riwi.demo.api.dto.response.BasicUserResp;
import com.riwi.demo.api.dto.response.MessagesResp;
import com.riwi.demo.domain.entity.Courses;
import com.riwi.demo.domain.entity.Messages;
import com.riwi.demo.domain.entity.Users;
import com.riwi.demo.domain.repositories.CoursesRepository;
import com.riwi.demo.domain.repositories.MessagesRepository;
import com.riwi.demo.domain.repositories.UsersRepository;
import com.riwi.demo.infrastructure.abstract_services.IMessagesService;
import com.riwi.demo.utils.enums.exceptions.BadRequestException;
import com.riwi.demo.utils.messages.ErrorMessage;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class MessagesService implements IMessagesService {

    @Autowired
    private final MessagesRepository messagesRepository;

    @Autowired
    private final UsersRepository usersRepository;

    @Autowired
    private final CoursesRepository coursesRepository;

    @Override
    public MessagesResp create(MessagesReq request) {

        Users receiver = this.usersRepository.findById(request.getReceiver_id())
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("emisor")));

        Users sender = this.usersRepository.findById(request.getSender_id())
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("receptor")));

        Courses course = this.coursesRepository.findById(request.getCourse_id())
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("curso")));

        Messages message = this.requestToEntity(request);

        message.setReceiver_id(receiver);
        message.setSender_id(sender);
        message.setCourse_id(course);

        return this.entityToResponse(this.messagesRepository.save(message));
    }

    @Override
    public MessagesResp get(String id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public MessagesResp update(MessagesReq request, String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Page<MessagesResp> getAll(int Page, int size) {
        if (Page < 0)
            Page = 0;

        PageRequest pagination = PageRequest.of(Page, size);

        return this.messagesRepository.findAll(pagination)
                .map(message -> this.entityToResponse(message));
    }

    @Override
    public List<MessagesResp> getMessagesByUsers(String senderId, String receiverId) {
        if (isEmpty(senderId) || isEmpty(receiverId)) {
            throw new BadRequestException("El sender ID o el receiver ID están vacíos");
        }
    
        List<Messages> messages = messagesRepository.findAll();
        List<Messages> filteredMessages = new ArrayList<>();
        for (Messages message : messages) {
            if (senderId.equals(message.getSender_id().getUser_id()) && receiverId.equals(message.getReceiver_id().getUser_id())) {
                filteredMessages.add(message);
            }
        }
    
        return filteredMessages.stream()
                .map(this::entityToResponse)
                .collect(Collectors.toList());
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private MessagesResp entityToResponse(Messages entity) {

        BasicUserResp receiver = new BasicUserResp();
        BeanUtils.copyProperties(entity.getReceiver_id(), receiver);

        BasicUserResp sender = new BasicUserResp();
        BeanUtils.copyProperties(entity.getSender_id(), sender);

        BasicCourseResp course = new BasicCourseResp();
        BeanUtils.copyProperties(entity.getCourse_id(), course);

        return MessagesResp.builder()
                .message_id(entity.getMessage_id())
                .message_content(entity.getMessage_content())
                .send_date(entity.getSend_date())
                .receiver_id(receiver)
                .sender_id(sender)
                .course_id(course)
                .build();
    }

    private Messages requestToEntity(MessagesReq request) {

        Users receiver = this.usersRepository.findById(request.getReceiver_id())
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("emisor")));

        Users sender = this.usersRepository.findById(request.getSender_id())
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("receptor")));

        Courses course = this.coursesRepository.findById(request.getCourse_id())
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("curso")));

        return Messages.builder()
                .message_content(request.getMessage_content())
                .receiver_id(receiver)
                .sender_id(sender)
                .course_id(course)
                .send_date(request.getSend_date())
                .build();
    }

    private Messages find(String id) {
        return this.messagesRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("Servicio")));
    }

}

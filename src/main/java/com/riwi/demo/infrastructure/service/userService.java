package com.riwi.demo.infrastructure.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.riwi.demo.api.dto.request.userReq;
import com.riwi.demo.api.dto.response.coursesToUsers;
import com.riwi.demo.api.dto.response.userResp;
import com.riwi.demo.domain.entity.courses;
import com.riwi.demo.domain.entity.users;
import com.riwi.demo.domain.repositories.usersRepositoy;
import com.riwi.demo.infrastructure.abstract_services.IUserService;
import com.riwi.demo.utils.enums.Role;
import com.riwi.demo.utils.enums.exceptions.BadRequestException;
import com.riwi.demo.utils.messages.ErrorMessage;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class userService implements IUserService {

    @Autowired
    private final usersRepositoy usersRepositoy;

    @Override
    public userResp create(userReq request) {
        users user = this.requestToEntity(request);
        user.setCourses(new ArrayList<>());
        return this.entityToResp(this.usersRepositoy.save(user));
    }

    @Override
    public userResp get(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public userResp update(userReq request, String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Page<userResp> getAll(int Page, int size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    private userResp entityToResp(users entity) {

        List<coursesToUsers> courses = entity.getCourses()
                .stream()
                .map(this::entityToResponseCourses)
                .collect(Collectors.toList());

        return userResp.builder()
                .user_id(entity.getUser_id())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .full_name(entity.getFull_name())
                .role(entity.getRole())
                .courses(courses)
                .build();
    }

    private coursesToUsers entityToResponseCourses(courses entity) {

        return coursesToUsers.builder()
                .course_id(entity.getCourse_id())
                .course_name(entity.getCourse_name())
                .description(entity.getDescription())
                .instructor_id(entity.getInstructor())
                .build();
    }

    private users requestToEntity(userReq user) {
        return users.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .full_name(user.getFull_name())
                .role(user.getRole().equals("STUDENT") ? Role.STUDENT : Role.INSTRUCTOR)
                .build();
    }

    private users find(String id) {
        return this.usersRepositoy.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("user")));
    }
}

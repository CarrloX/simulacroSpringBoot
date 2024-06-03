package com.riwi.demo.infrastructure.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.riwi.demo.api.dto.request.UserReq;
import com.riwi.demo.api.dto.response.UserInstructorResp;
import com.riwi.demo.api.dto.response.BasicCourseResp;
import com.riwi.demo.api.dto.response.BasicLessonResp;
import com.riwi.demo.api.dto.response.CoursesToUsers;
import com.riwi.demo.api.dto.response.UserResp;
import com.riwi.demo.domain.entity.Courses;
import com.riwi.demo.domain.entity.Users;
import com.riwi.demo.domain.repositories.UsersRepository;
import com.riwi.demo.infrastructure.abstract_services.IUserService;
import com.riwi.demo.utils.enums.Role;
import com.riwi.demo.utils.enums.exceptions.BadRequestException;
import com.riwi.demo.utils.messages.ErrorMessage;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private final UsersRepository usersRepositoy;

    @Override
    public UserResp create(UserReq request) {
        Users user = this.requestToEntity(request);
        user.setCourses(new ArrayList<>());
        return this.entityToResp(this.usersRepositoy.save(user));
    }

    @Override
    public UserResp get(String id) {
        return this.entityToResp(this.find(id));
    }

    @Override
    public UserResp update(UserReq request, String id) {
        Users user = this.find(id);

        Users userUpdate = this.requestToEntity(request);
        userUpdate.setUser_id(id);
        userUpdate.setCourses(user.getCourses());

        return this.entityToResp(this.usersRepositoy.save(userUpdate));
    }

    @Override
    public void delete(String id) {
        Users user = this.find(id);
        this.usersRepositoy.delete(user);
    }

    @Override
    public Page<UserResp> getAll(int Page, int size) {
        if (Page < 0)
            Page = 0;

        PageRequest pagination = PageRequest.of(Page, size);

        return this.usersRepositoy.findAll(pagination)
                .map(user -> this.entityToResp(user));
    }

    @Override
    public List<BasicCourseResp> getCoursesByUserId(@PathVariable String userId) {
        Users user = this.find(userId);
        return user.getCourses().stream()
                .map(course -> new BasicCourseResp(course.getCourse_id(), course.getCourse_name(),
                        course.getDescription()))
                .collect(Collectors.toList());
    }

    private UserResp entityToResp(Users entity) {

        List<CoursesToUsers> courses = entity.getCourses()
                .stream()
                .map(this::entityToResponseCourses)
                .collect(Collectors.toList());

        return UserResp.builder()
                .user_id(entity.getUser_id())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .full_name(entity.getFull_name())
                .role(entity.getRole())
                .courses(courses)
                .build();
    }

    private CoursesToUsers entityToResponseCourses(Courses entity) {
        UserInstructorResp instructor = UserInstructorResp.builder()
                .user_id(entity.getInstructor().getUser_id())
                .username(entity.getInstructor().getUsername())
                .email(entity.getInstructor().getEmail())
                .full_name(entity.getInstructor().getFull_name())
                .role(entity.getInstructor().getRole())
                .build();

        return CoursesToUsers.builder()
                .course_id(entity.getCourse_id())
                .course_name(entity.getCourse_name())
                .description(entity.getDescription())
                .instructor_id(instructor)
                .build();
    }

    private Users requestToEntity(UserReq user) {
        return Users.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .full_name(user.getFull_name())
                .role(user.getRole().equals("STUDENT") ? Role.STUDENT : Role.INSTRUCTOR)
                .build();
    }

    private Users find(String id) {
        return this.usersRepositoy.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("servicio")));
    }
}

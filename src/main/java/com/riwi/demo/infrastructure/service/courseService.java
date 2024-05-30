package com.riwi.demo.infrastructure.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.riwi.demo.api.dto.request.coursesReq;
import com.riwi.demo.api.dto.response.basicUser;
import com.riwi.demo.api.dto.response.coursesResp;
import com.riwi.demo.api.dto.response.userResp;
import com.riwi.demo.domain.entity.courses;
import com.riwi.demo.domain.repositories.coursesRepository;
import com.riwi.demo.domain.repositories.usersRepository;
import com.riwi.demo.infrastructure.abstract_services.ICourseService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class courseService implements ICourseService {

    @Autowired
    private final coursesRepository coursesRepository;

    @Autowired
    private final usersRepository usersRepository;

    @Override
    public coursesResp create(coursesReq request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public coursesResp get(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public coursesResp update(coursesReq request, String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Page<coursesResp> getAll(int Page, int size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    private coursesResp entityToResponse(courses entity) {

        userResp instructor = new userResp();
        BeanUtils.copyProperties(entity.getInstructor(), instructor);

        basicUser user= new basicUser();
        BeanUtils.copyProperties(entity.getInstructor().getUser_id(), user);

        return coursesResp.builder()
                .course_id(entity.getCourse_id())
                .course_name(entity.getCourse_name())
                .description(entity.getDescription())
                .instructor_id(entity.getInstructor())
                .user(user)
                .lessons(entity.getLessons())
                .build();

    }
}

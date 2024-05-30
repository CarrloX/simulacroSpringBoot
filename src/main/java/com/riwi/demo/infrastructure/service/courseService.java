package com.riwi.demo.infrastructure.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.demo.api.dto.request.coursesReq;
import com.riwi.demo.api.dto.response.coursesResp;
import com.riwi.demo.api.dto.response.userResp;
import com.riwi.demo.domain.entity.Courses;
import com.riwi.demo.domain.entity.Users;
import com.riwi.demo.domain.repositories.coursesRepository;
import com.riwi.demo.domain.repositories.usersRepository;
import com.riwi.demo.infrastructure.abstract_services.ICourseService;
import com.riwi.demo.utils.enums.exceptions.BadRequestException;
import com.riwi.demo.utils.messages.ErrorMessage;

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
        Users instructor = this.usersRepository.findById(request.getInstructor_id())
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("instructor")));

                if (instructor.getRole().name().equals("STUDENT")) {
                    throw new BadRequestException("No cumples el rol para se instructor de este curso");
                }

        Courses course = this.requestToEntity(request);

        course.setInstructor(instructor);
        return this.entityToResponse(this.coursesRepository.save(course));
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
        if (Page < 0)
            Page = 0;

        PageRequest pagination = PageRequest.of(Page, size);

        return this.coursesRepository.findAll(pagination)
                .map(course -> this.entityToResponse(course));
    }

    private coursesResp entityToResponse(Courses entity) {

        userResp instructor = new userResp();
        BeanUtils.copyProperties(entity.getInstructor(), instructor);

        return coursesResp.builder()
                .course_id(entity.getCourse_id())
                .course_name(entity.getCourse_name())
                .description(entity.getDescription())
                .instructor_id(entity.getInstructor())
                .lessons(entity.getLessons())
                .build();

    }

    private Courses requestToEntity(coursesReq request) {

        Users instructor = this.usersRepository.findById(request.getInstructor_id())
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("instructor")));

        return Courses.builder()
                .course_name(request.getCourse_name())
                .description(request.getDescription())
                .instructor(instructor)
                .build();
    }

    private Courses find(String id) {
        return this.coursesRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("Servicio")));
    }
}

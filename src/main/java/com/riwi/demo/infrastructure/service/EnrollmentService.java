package com.riwi.demo.infrastructure.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.riwi.demo.api.dto.request.EnrollmentReq;
import com.riwi.demo.api.dto.response.BasicCourseResp;
import com.riwi.demo.api.dto.response.BasicUserResp;
import com.riwi.demo.api.dto.response.EnrollmentsResp;
import com.riwi.demo.domain.entity.Courses;
import com.riwi.demo.domain.entity.Enrollments;
import com.riwi.demo.domain.entity.Users;
import com.riwi.demo.domain.repositories.CoursesRepository;
import com.riwi.demo.domain.repositories.EnrollmentsRepository;
import com.riwi.demo.domain.repositories.UsersRepository;
import com.riwi.demo.infrastructure.abstract_services.IEnrollmentService;
import com.riwi.demo.utils.enums.exceptions.BadRequestException;
import com.riwi.demo.utils.messages.ErrorMessage;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class EnrollmentService implements IEnrollmentService {

    @Autowired
    private final EnrollmentsRepository enrollmentsRepository;

    @Autowired
    private final UsersRepository usersRepository;

    @Autowired
    private final CoursesRepository coursesRepository;

    @Override
    public EnrollmentsResp create(EnrollmentReq request) {
        Users student = this.usersRepository.findById(request.getUser_id())
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("estudiante")));

        Courses course = this.coursesRepository.findById(request.getCourse_id())
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("curso")));        

        if (student.getRole().name().equals("INSTRUCTOR")) {
            throw new BadRequestException("No cumples el rol para ser estudiante de este curso");
        }

        Enrollments enrollment = this.requestToEntity(request);

        enrollment.setUser_id(student);
        enrollment.setCourse_id(course);
        return this.entityToResponse(this.enrollmentsRepository.save(enrollment));
    }

    @Override
    public EnrollmentsResp get(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public EnrollmentsResp update(EnrollmentReq request, String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Page<EnrollmentsResp> getAll(int Page, int size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    private EnrollmentsResp entityToResponse(Enrollments entity) {

        BasicUserResp student = new BasicUserResp();
        BeanUtils.copyProperties(entity.getUser_id(), student);

        BasicCourseResp course = new BasicCourseResp();
        BeanUtils.copyProperties(entity.getCourse_id(), course);

        return EnrollmentsResp.builder()
                .enrollment_id(entity.getEnrollment_id())
                .enrollment_date(entity.getEnrollment_date())
                .user(student)
                .course_id(course)
                .build();
    }

    private Enrollments requestToEntity(EnrollmentReq request) {

        Courses course = this.coursesRepository.findById(request.getCourse_id())
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("curso")));

        Users student = this.usersRepository.findById(request.getUser_id())
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("estudiante")));
                
                return Enrollments.builder()
                .enrollment_date(request.getEnrollment_date())
                .user_id(student)
                .course_id(course)
                .build();
    }

    private Enrollments find(String id) {
        return this.enrollmentsRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("Servicio")));
    }
}

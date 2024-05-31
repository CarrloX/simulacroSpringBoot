package com.riwi.demo.infrastructure.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.riwi.demo.api.dto.request.CoursesReq;
import com.riwi.demo.api.dto.response.BasicLessonResp;
import com.riwi.demo.api.dto.response.BasicUserResp;
import com.riwi.demo.api.dto.response.CoursesResp;
import com.riwi.demo.domain.entity.Courses;
import com.riwi.demo.domain.entity.Lessons;
import com.riwi.demo.domain.entity.Users;
import com.riwi.demo.domain.repositories.CoursesRepository;
import com.riwi.demo.domain.repositories.UsersRepository;
import com.riwi.demo.infrastructure.abstract_services.ICourseService;
import com.riwi.demo.utils.enums.exceptions.BadRequestException;
import com.riwi.demo.utils.messages.ErrorMessage;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class CourseService implements ICourseService {

    @Autowired
    private final CoursesRepository coursesRepository;

    @Autowired
    private final UsersRepository usersRepository;

    @Override
    public CoursesResp create(CoursesReq request) {
        Users instructor = this.usersRepository.findById(request.getInstructor_id())
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("instructor")));

        if (instructor.getRole().name().equals("STUDENT")) {
            throw new BadRequestException("No cumples el rol para ser instructor de este curso");
        }

        Courses course = this.requestToEntity(request);

        course.setInstructor(instructor);
        return this.entityToResponse(this.coursesRepository.save(course));
    }

    @Override
    public CoursesResp get(String id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public CoursesResp update(CoursesReq request, String id) {

        Courses course = this.find(id);

        Users instructor = this.usersRepository.findById(request.getInstructor_id())
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("instructor")));

        if (instructor.getRole().name().equals("STUDENT")) {
            throw new BadRequestException("el id insertado no cumple con el rol para ser instructor de este curso");
        }

        course = this.requestToEntity(request);

        course.setInstructor(instructor);
        course.setCourse_id(id);

        return this.entityToResponse(this.coursesRepository.save(course));
    }

    @Override
    public void delete(String id) {
        this.coursesRepository.delete(this.find(id));
    }

    @Override
    public Page<CoursesResp> getAll(int Page, int size) {
        if (Page < 0)
            Page = 0;

        PageRequest pagination = PageRequest.of(Page, size);

        return this.coursesRepository.findAll(pagination)
                .map(course -> this.entityToResponse(course));
    }

    @Override
    public List<BasicLessonResp> getLessonsByCourseId(@PathVariable String courseId) {
        Courses course = this.find(courseId);
        return course.getLessons().stream()
                .map(lesson -> new BasicLessonResp(lesson.getLesson_id(), lesson.getLesson_title(),
                        lesson.getContent()))
                .collect(Collectors.toList());
    }

    private CoursesResp entityToResponse(Courses entity) {

        BasicUserResp instructor = new BasicUserResp();
        BeanUtils.copyProperties(entity.getInstructor(), instructor);

        List<BasicLessonResp> basicLessonResp = new ArrayList<>();
        //si queremos que imprima una lista de otra entidad aunque este vacia hacemos esta validacion
        if (entity.getLessons() != null) {
            for (Lessons lesson : entity.getLessons()) {
                basicLessonResp.add(new BasicLessonResp(lesson.getLesson_id(), lesson.getLesson_title(),
                        lesson.getContent()));
            }
        }

        return CoursesResp.builder()
                .course_id(entity.getCourse_id())
                .course_name(entity.getCourse_name())
                .description(entity.getDescription())
                .user(instructor)
                .basicLesson(basicLessonResp)
                .build();
    }

    private Courses requestToEntity(CoursesReq request) {

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

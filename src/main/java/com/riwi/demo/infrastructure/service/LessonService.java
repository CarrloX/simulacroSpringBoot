package com.riwi.demo.infrastructure.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.riwi.demo.api.dto.request.LessonReq;
import com.riwi.demo.api.dto.response.LessonResp;
import com.riwi.demo.api.dto.response.BasicActivityResp;
import com.riwi.demo.api.dto.response.BasicCourseResp;
import com.riwi.demo.domain.entity.Courses;
import com.riwi.demo.domain.entity.Lessons;
import com.riwi.demo.domain.repositories.CoursesRepository;
import com.riwi.demo.domain.repositories.LessonsRepository;
import com.riwi.demo.infrastructure.abstract_services.ILessonService;
import com.riwi.demo.utils.enums.exceptions.BadRequestException;
import com.riwi.demo.utils.messages.ErrorMessage;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class LessonService implements ILessonService {

    @Autowired
    private final LessonsRepository lessonRepository;

    @Autowired
    private final CoursesRepository coursesRepository;

    @Override
    public LessonResp create(LessonReq request) {
        Courses courses = this.coursesRepository.findById(request.getCourse_id())
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("curso")));

        Lessons lesson = this.requestToEntity(request);

        lesson.setCourse_id(courses);
        return this.entityToResponse(this.lessonRepository.save(lesson));
    }

    @Override
    public LessonResp get(String id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public LessonResp update(LessonReq request, String id) {

        Lessons lesson = this.find(id);

        Courses course = this.coursesRepository.findById(request.getCourse_id())
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("curso")));

        lesson = this.requestToEntity(request);

        lesson.setCourse_id(course);
        lesson.setLesson_id(id);

        return this.entityToResponse(this.lessonRepository.save(lesson));
    }

    @Override
    public void delete(String id) {
        this.lessonRepository.delete(this.find(id));
    }

    @Override
    public Page<LessonResp> getAll(int Page, int size) {
        if (Page < 0)
            Page = 0;

        PageRequest pagination = PageRequest.of(Page, size);

        return this.lessonRepository.findAll(pagination)
                .map(lesson -> this.entityToResponse(lesson));
    }

    @Override
    public List<BasicActivityResp> getActivityesByLessonId(@PathVariable String lessonID) {
        Lessons lesson = this.find(lessonID);
        return lesson.getActivityes().stream()
                .map(activity -> new BasicActivityResp(activity.getActivity_id(), activity.getActivity_title(),
                        activity.getDescription(),activity.getDue_date()))
                .collect(Collectors.toList());
    }

    private LessonResp entityToResponse(Lessons entity) {

        BasicCourseResp course = new BasicCourseResp();
        BeanUtils.copyProperties(entity.getCourse_id(), course);

        return LessonResp.builder()
                .lesson_id(entity.getLesson_id())
                .lesson_title(entity.getLesson_title())
                .content(entity.getContent())
                .course(course)
                .build();
    }

    private Lessons requestToEntity(LessonReq request) {

        Courses course = this.coursesRepository.findById(request.getCourse_id())
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("curso")));

        return Lessons.builder()
                .lesson_title(request.getLesson_title())
                .content(request.getContent())
                .course_id(course)
                .build();
    }

    private Lessons find(String id) {
        return this.lessonRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("Servicio")));
    }

}

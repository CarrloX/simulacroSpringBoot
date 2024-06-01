package com.riwi.demo.infrastructure.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.riwi.demo.api.dto.request.ActivityesReq;
import com.riwi.demo.api.dto.response.ActivityesResp;
import com.riwi.demo.api.dto.response.BasicLessonResp;
import com.riwi.demo.api.dto.response.BasicSubmissionResp;
import com.riwi.demo.domain.entity.Activityes;
import com.riwi.demo.domain.entity.Lessons;
import com.riwi.demo.domain.entity.Submissions;
import com.riwi.demo.domain.repositories.ActivityesRepository;
import com.riwi.demo.domain.repositories.LessonsRepository;
import com.riwi.demo.domain.repositories.SubmissionsRepository;
import com.riwi.demo.infrastructure.abstract_services.IActivityService;
import com.riwi.demo.utils.enums.exceptions.BadRequestException;
import com.riwi.demo.utils.messages.ErrorMessage;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class ActivityService implements IActivityService {

    @Autowired
    private final ActivityesRepository activityRepository;

    @Autowired
    private final SubmissionsRepository submissionsRepository;

    @Autowired
    private final LessonsRepository lessonsRepository;

    @Override
    public ActivityesResp create(ActivityesReq request) {
        Lessons lesson = this.lessonsRepository.findById(request.getLesson_id())
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("lesson")));

        Activityes activity = this.requestToEntity(request);

        activity.setLesson_id(lesson);
        return this.entityToResponse(this.activityRepository.save(activity));
    }

    @Override
    public ActivityesResp get(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public ActivityesResp update(ActivityesReq request, String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Page<ActivityesResp> getAll(int Page, int size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    private ActivityesResp entityToResponse(Activityes entity) {

        BasicLessonResp lesson = new BasicLessonResp();
        BeanUtils.copyProperties(entity.getLesson_id(), lesson);

        List<BasicSubmissionResp> basicSubmissionResps = new ArrayList<>();
        // si queremos que imprima una lista de otra entidad aunque este vacia hacemos
        // esta validacion
        if (entity.getSubmissions() != null) {
            for (Submissions submission : entity.getSubmissions()) {
                basicSubmissionResps.add(new BasicSubmissionResp(submission.getSubmission_id(), submission.getContent(),
                        submission.getSubmission_date(), submission.getGrade()));
            }
        }

        return ActivityesResp.builder()
                .activity_id(entity.getActivity_id())
                .activity_title(entity.getActivity_title())
                .description(entity.getDescription())
                .due_date(entity.getDue_date())
                .lesson_id(lesson)
                .submissions(basicSubmissionResps)
                .build();
    }

    private Activityes requestToEntity(ActivityesReq request) {

        Lessons lesson = this.lessonsRepository.findById(request.getLesson_id())
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("lesson")));

        return Activityes.builder()
                .activity_title(request.getActivity_title())
                .description(request.getDescription())
                .due_date(request.getDue_date())
                .lesson_id(lesson)
                .build();
    }

    private Activityes find(String id) {
        return this.activityRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("Servicio")));
    }

}

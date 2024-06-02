package com.riwi.demo.infrastructure.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.demo.api.dto.request.SubmissionReq;
import com.riwi.demo.api.dto.response.BasicActivityResp;
import com.riwi.demo.api.dto.response.BasicUserResp;
import com.riwi.demo.api.dto.response.SubmissionResp;
import com.riwi.demo.domain.entity.Activityes;
import com.riwi.demo.domain.entity.Submissions;
import com.riwi.demo.domain.entity.Users;
import com.riwi.demo.domain.repositories.ActivityesRepository;
import com.riwi.demo.domain.repositories.SubmissionsRepository;
import com.riwi.demo.domain.repositories.UsersRepository;
import com.riwi.demo.infrastructure.abstract_services.ISubmisionService;
import com.riwi.demo.utils.enums.exceptions.BadRequestException;
import com.riwi.demo.utils.messages.ErrorMessage;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class SubmissionService implements ISubmisionService {

    @Autowired
    private final SubmissionsRepository submissionsRepository;

    @Autowired
    private final UsersRepository usersRepository;

    @Autowired
    private final ActivityesRepository activityesRepository;

    @Override
    public SubmissionResp create(SubmissionReq request) {
        Users student = this.usersRepository.findById(request.getUser_id())
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("estudiante")));

        Activityes activity = this.activityesRepository.findById(request.getActivity_id())
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("actividad")));

        if (student.getRole().name().equals("INSTRUCTOR")) {
            throw new BadRequestException("No cumples el rol para ser estudiante de este curso");
        }

        Submissions submission = this.requestToEntity(request);

        submission.setUser_id(student);
        submission.setActivity_id(activity);
        return this.entityToResponse(this.submissionsRepository.save(submission));
    }

    private Submissions find(String id) {
        return this.submissionsRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("Servicio")));
    }

    @Override
    public SubmissionResp update(SubmissionReq request, String id) {
        
        Submissions submission = this.find(id);

        Users student = this.usersRepository.findById(request.getUser_id())
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("estudiante")));

        if (student.getRole().name().equals("INSTRUCTOR")) {
            throw new BadRequestException("No cumples el rol para ser estudiante de este curso");
        }

        Activityes activity = this.activityesRepository.findById(request.getActivity_id())
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("actividad")));

        submission = this.requestToEntity(request);
        
        submission.setUser_id(student);
        submission.setActivity_id(activity);
        submission.setSubmission_id(id);
        
        return this.entityToResponse(this.submissionsRepository.save(submission));
    }

    @Override
    public SubmissionResp get(String id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public void delete(String id) {
        this.submissionsRepository.delete(this.find(id));
    }

    @Override
    public Page<SubmissionResp> getAll(int Page, int size) {
        if (Page < 0)
            Page = 0;

        PageRequest pagination = PageRequest.of(Page, size);

        return this.submissionsRepository.findAll(pagination)
                .map(submission -> this.entityToResponse(submission));
    }

    private SubmissionResp entityToResponse(Submissions entity) {

        BasicUserResp student = new BasicUserResp();
        BeanUtils.copyProperties(entity.getUser_id(), student);

        BasicActivityResp activity = new BasicActivityResp();
        if (entity.getActivity_id() != null) {
            BeanUtils.copyProperties(entity.getActivity_id(), activity);
        }
        BeanUtils.copyProperties(entity.getActivity_id(), activity);

        return SubmissionResp.builder()
                .submission_id(entity.getSubmission_id())
                .content(entity.getContent())
                .submission_date(entity.getSubmission_date())
                .grade(entity.getGrade())
                .user(student)
                .activity_id(activity)
                .build();
    }

    private Submissions requestToEntity(SubmissionReq request) {

        Users student = this.usersRepository.findById(request.getUser_id())
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("estudiante")));

        Activityes activity = this.activityesRepository.findById(request.getActivity_id())
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("actividad")));

        return Submissions.builder()
                .content(request.getContent())
                .submission_date(request.getSubmission_date())
                .grade(request.getGrade())
                .user_id(student)
                .activity_id(activity)
                .build();
    }
}

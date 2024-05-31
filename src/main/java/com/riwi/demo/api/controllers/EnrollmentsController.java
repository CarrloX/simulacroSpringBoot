package com.riwi.demo.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.demo.api.dto.request.EnrollmentReq;
import com.riwi.demo.api.dto.response.EnrollmentsResp;
import com.riwi.demo.infrastructure.abstract_services.IEnrollmentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/enrollments")
@AllArgsConstructor
public class EnrollmentsController {

    @Autowired
    private final IEnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<EnrollmentsResp> insert(
            @Validated @RequestBody EnrollmentReq request) {
        return ResponseEntity.ok(this.enrollmentService.create(request));
    }
}

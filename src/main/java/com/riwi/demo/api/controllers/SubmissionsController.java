package com.riwi.demo.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.demo.api.dto.request.SubmissionReq;
import com.riwi.demo.api.dto.response.SubmissionResp;
import com.riwi.demo.infrastructure.abstract_services.ISubmisionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/submissions")
@AllArgsConstructor
public class SubmissionsController {

    @Autowired
    private final ISubmisionService submisionService;

    @PostMapping
    public ResponseEntity<SubmissionResp> insert(
            @Validated @RequestBody SubmissionReq request) {
        return ResponseEntity.ok(this.submisionService.create(request));
    }
}

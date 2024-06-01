package com.riwi.demo.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.demo.api.dto.request.ActivityesReq;
import com.riwi.demo.api.dto.response.ActivityesResp;
import com.riwi.demo.infrastructure.abstract_services.IActivityService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/activityes")
@AllArgsConstructor
public class ActivityesController {

    @Autowired
    private IActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityesResp> insert(
            @Validated @RequestBody ActivityesReq request) {
        return ResponseEntity.ok(this.activityService.create(request));
    }
}

package com.riwi.demo.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.demo.api.dto.request.ActivityesReq;
import com.riwi.demo.api.dto.request.CoursesReq;
import com.riwi.demo.api.dto.response.ActivityesResp;
import com.riwi.demo.api.dto.response.CoursesResp;
import com.riwi.demo.infrastructure.abstract_services.IActivityService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/activityes")
@AllArgsConstructor
public class ActivityesController {

    @Autowired
    private IActivityService activityService;

    @GetMapping
    public ResponseEntity<Page<ActivityesResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(this.activityService.getAll(page - 1, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ActivityesResp> get(
            @PathVariable String id) {
        return ResponseEntity.ok(this.activityService.get(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ActivityesResp> update(
            @Validated @RequestBody ActivityesReq request,
            @PathVariable String id) {
        return ResponseEntity.ok(this.activityService.update(request, id));
    }

    @PostMapping
    public ResponseEntity<ActivityesResp> insert(
            @Validated @RequestBody ActivityesReq request) {
        return ResponseEntity.ok(this.activityService.create(request));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.activityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

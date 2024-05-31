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

    @GetMapping
    public ResponseEntity<Page<EnrollmentsResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(this.enrollmentService.getAll(page - 1, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EnrollmentsResp> get(
            @PathVariable String id) {
        return ResponseEntity.ok(this.enrollmentService.get(id));
    }

    @PostMapping
    public ResponseEntity<EnrollmentsResp> insert(
            @Validated @RequestBody EnrollmentReq request) {
        return ResponseEntity.ok(this.enrollmentService.create(request));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<EnrollmentsResp> update(
            @Validated @RequestBody EnrollmentReq request,
            @PathVariable String id) {
        return ResponseEntity.ok(this.enrollmentService.update(request, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.enrollmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

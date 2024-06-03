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

    @GetMapping
    public ResponseEntity<Page<SubmissionResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(this.submisionService.getAll(page - 1, size));
    }

    @PostMapping
    public ResponseEntity<SubmissionResp> insert(
            @Validated @RequestBody SubmissionReq request) {
        return ResponseEntity.ok(this.submisionService.create(request));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SubmissionResp> get(
            @PathVariable String id) {
        return ResponseEntity.ok(this.submisionService.get(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SubmissionResp> update(
            @Validated @RequestBody SubmissionReq request,
            @PathVariable String id) {
        return ResponseEntity.ok(this.submisionService.update(request, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.submisionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

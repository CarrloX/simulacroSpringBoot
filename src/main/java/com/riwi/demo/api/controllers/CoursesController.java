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

import com.riwi.demo.api.dto.request.CoursesReq;
import com.riwi.demo.api.dto.response.UserResp;
import com.riwi.demo.api.dto.response.coursesResp;
import com.riwi.demo.infrastructure.abstract_services.ICourseService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/courses")
@AllArgsConstructor
public class CoursesController {

    @Autowired
    private final ICourseService courseService;

    @GetMapping
    public ResponseEntity<Page<coursesResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(this.courseService.getAll(page - 1, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<coursesResp> get(
            @PathVariable String id) {
        return ResponseEntity.ok(this.courseService.get(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<coursesResp> update(
            @Validated @RequestBody CoursesReq request,
            @PathVariable String id) {
        return ResponseEntity.ok(this.courseService.update(request, id));
    }

    @PostMapping
    public ResponseEntity<coursesResp> insert(
            @Validated @RequestBody CoursesReq request) {
        return ResponseEntity.ok(this.courseService.create(request));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.courseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

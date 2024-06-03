package com.riwi.demo.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.demo.api.dto.errors.BaseErrorResponse;
import com.riwi.demo.api.dto.request.CoursesReq;
import com.riwi.demo.api.dto.response.BasicLessonResp;
import com.riwi.demo.api.dto.response.BasicMessagesResp;
import com.riwi.demo.api.dto.response.CoursesResp;
import com.riwi.demo.infrastructure.abstract_services.ICourseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/courses")
@AllArgsConstructor
public class CoursesController {

    @Autowired
    private final ICourseService courseService;

    @Operation(summary = "obtiene toda la lista de los cursos de forma paginada")

    @GetMapping
    public ResponseEntity<Page<CoursesResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(this.courseService.getAll(page - 1, size));
    }

    @ApiResponse(responseCode = "400", description = "cuando el ID no es valido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })

    @GetMapping(path = "/{id}")
    public ResponseEntity<CoursesResp> get(
            @PathVariable String id) {
        return ResponseEntity.ok(this.courseService.get(id));
    }

    @ApiResponse(responseCode = "400", description = "cuando el request no es valido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = BaseErrorResponse.class))
    })

    @PutMapping(path = "/{id}")
    public ResponseEntity<CoursesResp> update(
            @Validated @RequestBody CoursesReq request,
            @PathVariable String id) {
        return ResponseEntity.ok(this.courseService.update(request, id));
    }

    @ApiResponse(responseCode = "400", description = "cuando el request no es valido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = BaseErrorResponse.class))
    })

    @PostMapping
    public ResponseEntity<CoursesResp> insert(
            @Validated @RequestBody CoursesReq request) {
        return ResponseEntity.ok(this.courseService.create(request));
    }

    @ApiResponse(responseCode = "400", description = "cuando el ID no es valido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = BaseErrorResponse.class))
    })

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.courseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ApiResponse(responseCode = "400", description = "cuando el ID no es valido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })

    @GetMapping(path = "/{id}/lessons")
    public ResponseEntity<List<BasicLessonResp>> getLessonsByCourseId(@PathVariable String id) {
        return ResponseEntity.ok(courseService.getLessonsByCourseId(id));
    }

    @ApiResponse(responseCode = "400", description = "cuando el ID no es valido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })

    @GetMapping(path = "/{id}/messages")
    public ResponseEntity<List<BasicMessagesResp>> getMessagesByCourseId(@PathVariable String id) {
        return ResponseEntity.ok(courseService.getMessagesByCourseId(id));
    }

}

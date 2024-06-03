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
import com.riwi.demo.api.dto.request.UserReq;
import com.riwi.demo.api.dto.response.BasicCourseResp;
import com.riwi.demo.api.dto.response.UserResp;
import com.riwi.demo.infrastructure.abstract_services.IUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
public class UsersController {

    @Autowired
    private final IUserService userService;

    @Operation(summary = "obtiene toda la lista de usuarios de forma paginada")

    @GetMapping
    public ResponseEntity<Page<UserResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(this.userService.getAll(page - 1, size));
    }

    @ApiResponse(responseCode = "400", description = "cuando el ID no es valido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResp> get(
            @PathVariable String id) {
        return ResponseEntity.ok(this.userService.get(id));
    }

    @ApiResponse(responseCode = "400", description = "cuando el request no es valido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = BaseErrorResponse.class))
    })

    @PostMapping
    public ResponseEntity<UserResp> insert(
            @Validated @RequestBody UserReq request) {
        return ResponseEntity.ok(this.userService.create(request));
    }

    @ApiResponse(responseCode = "400", description = "cuando el request no es valido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = BaseErrorResponse.class))
    })

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserResp> update(
            @Validated @RequestBody UserReq request,
            @PathVariable String id) {
        return ResponseEntity.ok(this.userService.update(request, id));
    }

    @ApiResponse(responseCode = "400", description = "cuando el ID no es valido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = BaseErrorResponse.class))
    })

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ApiResponse(responseCode = "400", description = "cuando el ID no es valido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })

    @GetMapping(path = "/{id}/courses")
    public ResponseEntity<List<BasicCourseResp>> getCoursesByUserId(@PathVariable String id) {
        return ResponseEntity.ok(userService.getCoursesByUserId(id));
    }
}

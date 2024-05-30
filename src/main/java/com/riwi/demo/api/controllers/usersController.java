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

import com.riwi.demo.api.dto.request.userReq;
import com.riwi.demo.api.dto.response.userResp;
import com.riwi.demo.infrastructure.abstract_services.IUserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
public class usersController {

    @Autowired
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<Page<userResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(this.userService.getAll(page - 1, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<userResp> get(
            @PathVariable String id) {
        return ResponseEntity.ok(this.userService.get(id));
    }

    @PostMapping
    public ResponseEntity<userResp> insert(
            @Validated @RequestBody userReq request) {
        return ResponseEntity.ok(this.userService.create(request));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<userResp> update(
            @Validated @RequestBody userReq request,
            @PathVariable String id) {
        return ResponseEntity.ok(this.userService.update(request, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

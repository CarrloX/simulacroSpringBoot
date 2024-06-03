package com.riwi.demo.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.demo.api.dto.errors.BaseErrorResponse;
import com.riwi.demo.api.dto.request.MessagesReq;
import com.riwi.demo.api.dto.response.MessagesResp;
import com.riwi.demo.infrastructure.abstract_services.IMessagesService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/messages")
@AllArgsConstructor
public class MessagesController {

    @Autowired
    private final IMessagesService messagesService;

    @ApiResponse(responseCode = "400", description = "cuando el request no es valido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = BaseErrorResponse.class))
    })

    @PostMapping
    public ResponseEntity<MessagesResp> insert(
            @Validated @RequestBody MessagesReq request) {
        return ResponseEntity.ok(this.messagesService.create(request));
    }

    @ApiResponse(responseCode = "400", description = "cuando el ID no es valido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })

    @GetMapping
    public ResponseEntity<List<MessagesResp>> getMessagesByUsers(
            @RequestParam String sender_id, @RequestParam String receiver_id) {
        List<MessagesResp> messages = messagesService.getMessagesByUsers(sender_id, receiver_id);
        return ResponseEntity.ok(messages);
    }
}

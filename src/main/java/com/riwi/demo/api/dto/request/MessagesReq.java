package com.riwi.demo.api.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessagesReq {
    @NotBlank(message = "el id del emisor es requerido")
    private String sender_id;
    @NotBlank(message = "el id del receptor es requerido")
    private String receiver_id;
    @NotBlank(message = "el id del curso es requerido")
    private String course_id;
    @NotBlank(message = "el contenido del mensaje es requerido")
    private String message_content;
    @NotBlank(message = "la fecha de envio es requerida")
    private LocalDate send_date;
}

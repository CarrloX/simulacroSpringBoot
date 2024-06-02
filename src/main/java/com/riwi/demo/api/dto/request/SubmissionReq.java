package com.riwi.demo.api.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionReq {
    @NotBlank(message = "el contenido de la entrega es requerido")
    private String content;
    @NotNull(message = "La fecha de la entrega es requerida")
    private LocalDate submission_date;
    @NotNull(message = "La calificación es requerida")
    private double grade;
    @NotBlank(message = "el id del usuario es requerido")
    private String user_id;
    @NotBlank(message = "el id de la actividad es requerido")
    private String activity_id;
}

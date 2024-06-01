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
public class ActivityesReq {
    @NotBlank(message = "el nombre de la actividad es requerida")
    private String activity_title;
    @NotBlank(message = "la descripción de la actividad es requerida")
    private String description;
    @NotBlank(message = "la fecha de vencimiento es requerida")
    private LocalDate due_date;
    @NotBlank(message = "el id de la lección es requerida")
    private String lesson_id;
}

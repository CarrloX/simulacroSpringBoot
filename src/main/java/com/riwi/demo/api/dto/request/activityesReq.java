package com.riwi.demo.api.dto.request;

import java.sql.Date;

import com.riwi.demo.domain.entity.lessons;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class activityesReq {
    @NotBlank(message = "el nombre de la actividad es requerida")
    private String activity_title;
    @NotBlank(message = "la descripción de la actividad es requerida")
    private String description;
    @NotBlank(message = "la fecha de vencimiento es requerida")
    private Date due_date;
    @NotBlank(message = "el id de la lección es requerida")
    private lessons lesson_id;
}

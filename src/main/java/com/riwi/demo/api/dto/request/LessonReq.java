package com.riwi.demo.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonReq {
    @NotBlank(message = "el titulo de la lección es requerido")
    private String lesson_title;
    @NotBlank(message = "el contenido es requerido")
    private String content;
    @NotBlank(message = "el id del curso es requerido")
    private String course_id;
}

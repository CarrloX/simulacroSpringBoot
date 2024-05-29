package com.riwi.demo.api.dto.request;

import com.riwi.demo.domain.entity.users;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class coursesReq {
    @NotBlank(message = "el nombre del curso es requerido")
    private String course_name;
    @NotBlank(message = "la descripcion del curso es requerida")
    private String description;
    @NotBlank(message = "el nombre del instructor es requerido")
    private users instructor_id;
}

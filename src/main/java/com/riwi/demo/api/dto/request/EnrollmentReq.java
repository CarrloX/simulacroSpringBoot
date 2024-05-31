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
public class EnrollmentReq {
    @NotBlank(message = "el id del usuario es requerido")
    private String user_id;
    @NotBlank(message = "el nombre del curso es requerido")
    private String course_id;
    @NotNull(message = "La fecha es requerida")
    private LocalDate enrollment_date;
}

package com.riwi.demo.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(
title = "API para administrar estudiantes,cursos,inscipciones,actividades,etc",
version ="1.0",
description = "Documentacion Api de estudiantes,cursos,inscipciones,actividades,etc"))
public class OpenApiConfig {
    
}

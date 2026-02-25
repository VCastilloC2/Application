package com.Developpers.Application.Model.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public record CrearUsuarioRequest (
        @NotBlank String nombres,
        @NotBlank String apellidos,
        @NotBlank String username,
        @NotBlank String password
){
}
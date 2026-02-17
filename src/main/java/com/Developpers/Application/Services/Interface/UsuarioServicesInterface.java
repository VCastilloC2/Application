package com.Developpers.Application.Services.Interface;

import com.Developpers.Application.Entity.Dto.Request.CrearUsuarioRequest;
import com.Developpers.Application.Entity.Dto.Response.UsuarioResponse;
import com.Developpers.Application.Entity.UsuarioEntity;
import java.util.List;
import java.util.Optional;

public interface UsuarioServicesInterface {

    // Obtener el usuario por Id
    Optional<UsuarioEntity> getUsuarioById(Long id);

    // Obtener todos los Usuarios
    List<UsuarioEntity> getUsuarioByAll();

    // Agregar un Usuario
    UsuarioResponse addUsuario(CrearUsuarioRequest usuarioRequest);

    // Agregar un Usuario
    String addCapitalLetter(String palabra);
}
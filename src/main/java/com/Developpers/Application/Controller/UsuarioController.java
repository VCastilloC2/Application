package com.Developpers.Application.Controller;

import com.Developpers.Application.Entity.Dto.Request.CrearUsuarioRequest;
import com.Developpers.Application.Entity.Dto.Response.UsuarioResponse;
import com.Developpers.Application.Entity.UsuarioEntity;
import com.Developpers.Application.Services.Interface.UsuarioServicesInterface;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioServicesInterface usuarioServicesInterface;


    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioResponse> crearUsuario(
            @Valid @RequestBody CrearUsuarioRequest request) {
        return ResponseEntity.ok(usuarioServicesInterface.addUsuario(request));
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioEntity>> obtenerTodos() {
        return ResponseEntity.ok(usuarioServicesInterface.getUsuarioByAll());
    }

    @GetMapping("/ver/usuarios")
    public String verUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioServicesInterface.getUsuarioByAll());
        return "Usuarios";
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioEntity> obtenerPorId(@PathVariable Long id) {

        return usuarioServicesInterface.getUsuarioById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/params")
    public ResponseEntity<UsuarioResponse> obtenerParams(@RequestParam String nombre,
                                                         @RequestParam String apellido) {
        UsuarioResponse response = usuarioServicesInterface.getParams(nombre, apellido);
        return ResponseEntity.ok(response);
    }

}
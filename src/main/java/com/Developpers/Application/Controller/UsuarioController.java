package com.Developpers.Application.Controller;

import com.Developpers.Application.Entity.Dto.Request.CrearUsuarioRequest;
import com.Developpers.Application.Entity.Dto.Response.UsuarioResponse;
import com.Developpers.Application.Entity.UsuarioEntity;
import com.Developpers.Application.Services.UsuarioServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServices usuarioServices;

    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioResponse> crearUsuario(
            @Valid @RequestBody CrearUsuarioRequest request) {
        return ResponseEntity.ok(usuarioServices.addUsuario(request));
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioEntity>> obtenerTodos() {
        return ResponseEntity.ok(usuarioServices.getUsuarioByAll());
    }

    @GetMapping("/ver/usuarios")
    public String verUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioServices.getUsuarioByAll());
        return "Usuarios";
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioEntity> obtenerPorId(@PathVariable Long id) {

        return usuarioServices.getUsuarioById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/params")
    public ResponseEntity<Map<String, String>> obtenerParams(
            @RequestParam String nombre,
            @RequestParam String apellido) {

        Map<String, String> response = new HashMap<>();
        response.put("nombreCompleto: ", nombre + " " + apellido);

        return ResponseEntity.ok(response);
    }

}
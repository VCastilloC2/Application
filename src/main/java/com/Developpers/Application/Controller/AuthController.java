package com.Developpers.Application.Controller;

import com.Developpers.Application.Services.UsuarioServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private UsuarioServices usuarioServices;

    @GetMapping({"/",""})
    public String Index() {
        return "Index";
    }

    @GetMapping("/auth/login")
    public String login(Model model,
                        @RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        @RequestParam(value = "success", required = false) String success) {
        if (error != null) {
            model.addAttribute("mensajeError", error);
        }
        if (logout != null) {
            model.addAttribute("mensajeExitoso", "Sesión cerrada correctamente");
        }
        if (success != null) {
            model.addAttribute("loginSuccess", true);
        }
        return "Login";
    }

    @GetMapping("/auth/registro")
    public String Registro(@RequestParam(value = "mensaje", required = false) String mensaje,
                           @RequestParam(value = "success", required = false) Boolean success,
                           Model model) {
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("success", success);
        return "Registro";
    }

}
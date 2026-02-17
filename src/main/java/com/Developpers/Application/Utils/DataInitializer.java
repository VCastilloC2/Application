package com.Developpers.Application.Utils;

import com.Developpers.Application.Entity.UsuarioEntity;
import com.Developpers.Application.Repository.UsuarioRepository;
import com.Developpers.Application.Services.UsuarioServices;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioServices usuarioservices;

    @Override
    public void run(String... args) {

        // Nobre del usuario a crear
        String username = "VictorCast2";

        // Verificar que existe el usuario
        if (!usuarioRepository.existsByUsername(username)) {

            // Patron de diseño ("Creacional para crear")
            UsuarioEntity admin = UsuarioEntity.builder()
                    .nombres(usuarioservices.addCapitalLetter("Víctor José"))
                    .apellidos(usuarioservices.addCapitalLetter("Castillo Castro"))
                    .username("VictorCast2")
                    .password("$2a$10$FymAG5buNSGC1NdozQ031O8dsoDIQHdfPVT9subzCVrZaJFNLRtxa")
                    .isEnabled(true)
                    .accountNonLocked(true)
                    .accountNonExpired(true)
                    .credentialsNonExpired(true)
                    .build();

            // Guardamos el usuario creado
            usuarioRepository.save(admin);

            System.out.println("Usuario VictorCast2 creado correctamente");
        } else {
            System.out.println("Usuario VictorCast2 ya existe");
        }
    }

}
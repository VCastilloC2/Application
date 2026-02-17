package com.Developpers.Application.Services;

import com.Developpers.Application.Entity.Dto.Request.CrearUsuarioRequest;
import com.Developpers.Application.Entity.Dto.Response.UsuarioResponse;
import com.Developpers.Application.Entity.UsuarioEntity;
import com.Developpers.Application.Repository.UsuarioRepository;
import com.Developpers.Application.Services.Interface.UsuarioServicesInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServices implements UsuarioServicesInterface, UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    /**
     * @return Retorna una lista de todos los usuarios
     */
    @Override
    public List<UsuarioEntity> getUsuarioByAll() {
        return usuarioRepository.findAll();
    }

    /**
     * @return GeneralResponse
     */
    @Override
    public UsuarioResponse addUsuario(CrearUsuarioRequest usuarioRequest) {

        // Verificamos para ver si existe este username
        String username = usuarioRequest.username();
        if (usuarioRepository.existsByUsername(username)) {
            System.out.println("El usuario: " + username + " ya está registrado ... ");
            throw new RuntimeException("El usuario ya existe");
        }

        //Creamos el usuario
        UsuarioEntity usuario = UsuarioEntity.builder()
                .nombres(this.addCapitalLetter(usuarioRequest.nombres()))
                .apellidos(this.addCapitalLetter(usuarioRequest.apellidos()))
                .username(usuarioRequest.username())
                .password(passwordEncoder.encode(usuarioRequest.password()))
                .isEnabled(true)
                .accountNonLocked(true)
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .build();

        // Guardamos el usuario en la base de datos
        usuarioRepository.save(usuario);

        return new UsuarioResponse(
                usuario.getNombres(),
                usuario.getApellidos()
        );

    }

    /**
     * @return Una cadena en mayuscula...
     */
    @Override
    public String addCapitalLetter(String palabra) {
         return palabra.toUpperCase();
    }

    @Override
    public Optional<UsuarioEntity> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    /**
     * @param username
     * @return Una verificacion si el usuario se encuetra en la base de datos ...
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuario no encontrado: " + username)
                );
    }

}
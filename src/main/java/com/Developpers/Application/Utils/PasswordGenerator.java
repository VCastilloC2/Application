package com.Developpers.Application.Utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String password = "";
        String passwordEncoder = encoder.encode(password);

        System.out.println("La contraseña encriptada es: "+ "\n" + passwordEncoder + " ");
    }

}
package com.Developpers.Application.Configuration;

import com.Developpers.Application.Services.UsuarioServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Value("${security.rememberme.key}")
    private String rememberMeKey;

    @Value("${security.rememberme.token-validity}")
    private int rememberMeValidity;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                /* =========================
                   CSRF CONFIGURATION
                   ========================= */
                .csrf(AbstractHttpConfigurer::disable)

                /* =========================
                   Exception Handling
                   ========================= */
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/auth/access-denied")
                )

                /* =========================
                   SECURITY HEADERS
                    ========================= */
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                        .contentSecurityPolicy(csp ->
                                csp.policyDirectives("default-src 'self'"))
                )

                /* =========================
                   Remember Me Token
                   ========================= */
                .rememberMe(remember -> remember
                        .key(rememberMeKey)
                        .tokenValiditySeconds(rememberMeValidity)
                        .rememberMeParameter("remember-me")
                        .useSecureCookie(true)
                )

                /* =========================
                   SESSION MANAGEMENT
                   ========================= */
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::migrateSession)
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false)
                        .expiredUrl("/auth/login?expired")
                )

                /* =========================
                   AUTHORIZATION RULES
                   ========================= */
                .authorizeHttpRequests(auth -> auth

                        // Css
                        .requestMatchers("/Css/**").permitAll()

                        // Authentication
                        .requestMatchers("/auth/**").permitAll()

                        // Publicas
                        .requestMatchers(HttpMethod.GET, "/api/params").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/usuarios").permitAll()

                        // Privadas
                        .requestMatchers(HttpMethod.GET, "/api/usuarios/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/usuarios").authenticated()

                        // Todas
                        .anyRequest().authenticated()
                )

                /* =========================
                   FORM LOGIN
                   ========================= */
                .formLogin(form -> form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .successHandler((request, response, authentication) ->
                                response.sendRedirect("/"))
                        .failureUrl("/auth/login?error=true")
                        .permitAll()
                )
                .httpBasic(Customizer.withDefaults())

                /* =========================
                   LOGOUT
                   ========================= */
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessUrl("/auth/login?logout=true")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID", "remember-me")
                        .permitAll()
                )

                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UsuarioServices usuario) {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(usuario);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
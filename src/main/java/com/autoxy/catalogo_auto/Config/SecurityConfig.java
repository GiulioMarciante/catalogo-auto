package com.autoxy.catalogo_auto.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Classe di configurazione per la sicurezza dell'applicazione Spring Boot.
 * <p>
 * Configura la gestione delle autorizzazioni per gli endpoint API e disabilita CSRF
 * per garantire il corretto funzionamento delle richieste REST.
 * </p>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configura la catena dei filtri di sicurezza per gestire l'autenticazione e le autorizzazioni degli utenti.
     * <p>
     * - Permette l'accesso libero alla console H2.<br>
     * - Permette l'accesso in lettura (GET, POST, PUT, DELETE) alle API delle auto a tutti.<br>
     * - Richiede autenticazione per qualsiasi altra richiesta.<br>
     * - Disabilita CSRF per facilitare le richieste REST.<br>
     * - Abilita il supporto ai frame per la console H2.<br>
     * </p>
     *
     * @param http l'oggetto {@link HttpSecurity} per la configurazione della sicurezza
     * @return un'istanza di {@link SecurityFilterChain}
     * @throws Exception se si verifica un errore durante la configurazione della sicurezza
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // Permette l'accesso alla console H2
                        .requestMatchers(HttpMethod.GET, "/api/auto/**").permitAll() // GET aperto a tutti
                        .requestMatchers(HttpMethod.POST, "/api/auto/**").permitAll() // POST aperto a tutti
                        .requestMatchers(HttpMethod.PUT, "/api/auto/**").permitAll() // PUT aperto a tutti
                        .requestMatchers(HttpMethod.DELETE, "/api/auto/**").permitAll() // DELETE aperto a tutti
                        .anyRequest().authenticated() // Richiede autenticazione per qualsiasi altra richiesta
                )
                .csrf(AbstractHttpConfigurer::disable) // Disabilita la protezione CSRF per le richieste REST
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin) // Permette l'uso dei frame per la console H2
                );

        return http.build();
    }
}

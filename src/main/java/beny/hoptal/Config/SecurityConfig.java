package beny.hoptal.Config;

import beny.hoptal.data.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import beny.hoptal.Security.JwtFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtFilter jwtFilter;
    private final UserRepository userRepository; // ← add this

    public SecurityConfig(JwtFilter jwtFilter, UserRepository userRepository) {
        this.jwtFilter = jwtFilter;
        this.userRepository = userRepository;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByNomUtilisateur(username)
                .map(user -> org.springframework.security.core.userdetails.User
                        .withUsername(user.getNomUtilisateur())
                        .password(user.getMotDePasseHashe())
                        .roles(user.getRole().getNom())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/api/auth/login").permitAll()

                        .requestMatchers("/api/laborantins/**").hasRole("ADMIN")
                        .requestMatchers("/api/doctors/**").hasAnyRole("ADMIN")
                        .requestMatchers("/api/auth/activer-desactiver/**").hasRole("ADMIN")

                        .requestMatchers("/api/patients/**").hasAnyRole("ADMIN", "DOCTOR", "PATIENT")
                        .requestMatchers("/api/releves/**").hasAnyRole("ADMIN", "DOCTOR")
                        .requestMatchers("/api/prescriptions/**").hasAnyRole("ADMIN", "DOCTOR", "PATIENT")
                        .requestMatchers("/api/resultats-labo/**").hasAnyRole("ADMIN", "DOCTOR", "PATIENT", "LABORANTIN")

                        .anyRequest().authenticated()
                )

                // Add JWT filter before Spring's default auth filter
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
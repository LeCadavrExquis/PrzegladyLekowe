package pl.knab.Przeglad.Lekowy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) ->
                        auth
                                .requestMatchers(
                                        "/",
                                        "/login",
                                        "/index.html",
                                        "/static/**",
                                        "favicon.ico",
                                        "logo192.png",
                                        "logo512.png",
                                        "manifest.json",
                                        "robots.txt"
                                )
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                );
        return http.build();
    }
}

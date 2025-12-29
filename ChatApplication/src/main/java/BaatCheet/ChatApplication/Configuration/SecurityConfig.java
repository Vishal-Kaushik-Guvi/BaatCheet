package BaatCheet.ChatApplication.Configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // CSRF disabled for WebSocket compatibility
            .csrf(csrf -> csrf.disable())

            // Authorization rules
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(
                            "/signup",
                            "/login",
                            "/css/**",
                            "/js/**",
                            "/images/**",
                            "/ws/**"
                    ).permitAll()
                    .anyRequest().authenticated()
            )

            // Login configuration
            .formLogin(login -> login
                    .loginPage("/login")
                    .defaultSuccessUrl("/chat", true)
                    .permitAll()
            )

            // Logout configuration
            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
            )

            // Allow same-origin frames (useful for dev tools)
            .headers(headers ->
                    headers.frameOptions(frame -> frame.sameOrigin())
            );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

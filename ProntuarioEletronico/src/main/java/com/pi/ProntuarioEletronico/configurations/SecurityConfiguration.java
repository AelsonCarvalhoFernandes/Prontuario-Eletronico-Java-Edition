package com.pi.ProntuarioEletronico.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
                        throws Exception {
                return httpSecurity

                                .csrf(csrf -> csrf.disable())

                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers(HttpMethod.GET, "/", "index", "/about", "/login",
                                                                "/admin/createAdmin", "/static/**")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.POST, "/pacient/**").hasRole("DOCTOR")
                                                .requestMatchers(HttpMethod.GET, "/pacient/**", "/pacient/update/**")
                                                .hasRole("DOCTOR")
                                                .requestMatchers(HttpMethod.POST, "/home").hasRole("DOCTOR")
                                                .requestMatchers(HttpMethod.GET, "/home").hasRole("DOCTOR")
                                                .requestMatchers(HttpMethod.GET, "/prescriptions/**").hasRole("DOCTOR")
                                                .requestMatchers(HttpMethod.POST, "/prescriptions/**").hasRole("DOCTOR")
                                                .requestMatchers(HttpMethod.GET, "/laudos/**").hasRole("DOCTOR")
                                                .anyRequest().permitAll()

                                // .anyRequest().permitAll()
                                // .anyRequest().authenticated()
                                )

                                .formLogin(formLogin -> formLogin
                                                .loginPage("/login")
                                                .successHandler(successHandler())
                                                // .defaultSuccessUrl("/home", true)
                                                .permitAll())
                                .logout(logout -> logout
                                                .permitAll())
                                .build();
        }

        @Bean
        public AuthenticationSuccessHandler successHandler() {
                return (request, response, authentication) -> {

                        if (authentication.getAuthorities().stream()
                                        .anyMatch(a -> a.getAuthority().equals("ROLE_DOCTOR"))) {
                                response.sendRedirect("/pacient/all");
                        } else if (request.isUserInRole("ROLE_PACIENT")) {
                                response.sendRedirect("/pacient/create");
                        } else {
                                response.sendRedirect("/home");
                        }
                };
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}

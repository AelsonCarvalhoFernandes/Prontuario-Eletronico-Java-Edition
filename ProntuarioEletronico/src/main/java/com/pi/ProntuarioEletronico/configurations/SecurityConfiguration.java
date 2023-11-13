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

                                // .anyRequest().permitAll()
                                // .anyRequest().authenticated())
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

                        if (request.isUserInRole("ROLE_DOCTOR")) {
                                response.sendRedirect("/doctor/all");
                        } else if (request.isUserInRole("ROLE_PACIENT")) {
                                response.sendRedirect("/pacient/all");
                        } else {
                                System.out.println("Não é paciente nem médico" +
                                                authentication.getPrincipal());
                                response.sendRedirect("/pacient/all");
                        }
                };
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}

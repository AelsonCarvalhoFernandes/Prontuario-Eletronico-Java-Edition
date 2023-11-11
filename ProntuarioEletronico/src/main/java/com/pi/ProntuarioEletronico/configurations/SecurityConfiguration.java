package com.pi.ProntuarioEletronico.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
                        throws Exception {
                return httpSecurity

                                .csrf(csrf -> csrf.disable())
                                 .authorizeHttpRequests(authorize -> authorize
                                         .requestMatchers(HttpMethod.GET, "/", "/about", "/login").permitAll()
                                         .anyRequest().permitAll()
                                 )
                                 .formLogin(formLogin -> formLogin
                                 .loginPage("/login")
                                 .permitAll())
                                 .logout(logout -> logout
                                 .permitAll())
                                 .build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}

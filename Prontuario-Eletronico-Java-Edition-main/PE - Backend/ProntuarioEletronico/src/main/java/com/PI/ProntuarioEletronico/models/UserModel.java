package com.PI.ProntuarioEletronico.models;

import com.PI.ProntuarioEletronico.resources.enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class UserModel implements UserDetails {

    // Atributos dos usuarios
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank(message = "O campo nome é obrigatório")
    private String firstName;
    private String lastName;
    @Column(nullable = false)
    @NotBlank(message = "O campo email é obrigatório")
    private String email;
    @Column(nullable = false)
    @NotBlank(message = "O campo senha é obrigatório")
    private String password;
    private Roles roles;
    @Column(length = 15)
    @NotBlank(message = "O campo CPF é obrigatório")
    private String cpf;
    @Column(length = 9, nullable = false)
    @NotBlank(message = "O campo RG é obrigatório")
    private String rg;
    @Column(length = 15)
    private String cns;
    private LocalDate dateBirth;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters e Setters dos atributos
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Spring Security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (this.roles == Roles.administrador) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_MEDICO"),
                    new SimpleGrantedAuthority("ROLE_COLABORADOR"),
                    new SimpleGrantedAuthority("ROLE_USUARIO"));
        } else if (this.roles == Roles.medico) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_MEDICO"),
                    new SimpleGrantedAuthority("ROLE_COLABORADOR"),
                    new SimpleGrantedAuthority("ROLE_USUARIO"));
        } else if (this.roles == Roles.colaborador) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_COLABORADOR"),
                    new SimpleGrantedAuthority("ROLE_USUARIO"));
        } else {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_USUARIO"));
        }
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

package com.pi.ProntuarioEletronico.models.user;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.pi.ProntuarioEletronico.resources.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import org.springframework.security.access.method.P;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class UserModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName", length = 40)
    @NotBlank()
    private String firstName;

    @Column(name = "lastName", length = 100)
    @NotBlank
    private String lastName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "email")
    @NotBlank
    @Email
    private String email;

    @Column(name = "password")
    @NotBlank
    private String password;

    @Column(name = "rg", length = 10)
    @NotBlank
    private String rg;

    @Column(name = "cpf", length = 11)
    // @CPF
    private String cpf;

    private Role role;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /*
     * Construtores
     */

    public UserModel() {

    }

    public UserModel(@NotBlank String firstName, @NotBlank String lastName, @NotBlank String password,
            @NotBlank String rg, @CPF String cpf) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.rg = rg;
        this.cpf = cpf;
    }

    /*
     * Metodos getters e Setters
     */

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == Role.Administrator) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if (this.role == Role.Doctor) {
            return List.of(new SimpleGrantedAuthority("ROLE_DOCTOR"));
        } else if (this.role == Role.Collaborator) {
            return List.of(new SimpleGrantedAuthority("ROLE_COLLABORATOR"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_PACIENT"));
        }
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.email;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
}
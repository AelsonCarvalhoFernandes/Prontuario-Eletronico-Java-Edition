package com.pi.ProntuarioEletronico.models.user;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.br.CPF;

import com.pi.ProntuarioEletronico.resources.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName")
    @NotBlank()
    private String firstName;

    @Column(name = "lastName")
    @NotBlank
    private String lastName;

    @Column(name = "password")
    @NotBlank
    private String password;

    @Column(name = "rg")
    @NotBlank
    private String rg;

    @Column(name = "cpf")
    //@CPF
    private String cpf;

    @Column(name = "cns")
    private String cns;

    private Role role;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /*
     *  Construtores
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
     *  Metodos getters e Setters
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

    public String getPassword() {
        return password;
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

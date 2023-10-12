package com.pi.ProntuarioEletronico.models.user.typeUsers;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CollaboratorModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "formacao")
    private String formacao;

    @Column(name = "cargo")
    private String cargo;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    
    public CollaboratorModel() {

    }

    public CollaboratorModel(String formacao, String cargo, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.formacao = formacao;
        this.cargo = cargo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFormacao() {
        return formacao;
    }
    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
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

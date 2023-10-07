package com.PI.ProntuarioEletronico.models.UserModels;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users_doctor")
public class UserDoctorModel {

    // Atributos dos medicos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String crm;
    @Column(nullable = false)
    private String specialty;
    private LocalDate dateBirth;
    @Column(nullable = false)
    private LocalDate dateEmissionCrm;
    @OneToOne
    @JoinColumn(name = "id_user", nullable = false)
    private Long idUserModel;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Get and Setters dos atributos
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public LocalDate getDateEmissionCrm() {
        return dateEmissionCrm;
    }

    public void setDateEmissionCrm(LocalDate dateEmissionCrm) {
        this.dateEmissionCrm = dateEmissionCrm;
    }

    public Long getIdUserModel() {
        return idUserModel;
    }

    public void setIdUserModel(Long idUserModel) {
        this.idUserModel = idUserModel;
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
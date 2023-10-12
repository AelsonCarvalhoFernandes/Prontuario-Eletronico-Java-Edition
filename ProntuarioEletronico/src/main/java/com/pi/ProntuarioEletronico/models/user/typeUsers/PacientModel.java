package com.pi.ProntuarioEletronico.models.user.typeUsers;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.pi.ProntuarioEletronico.models.user.UserModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pacient")
public class PacientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Data_nascimento")
    private LocalDate dateBirth;

    @Column(name = "tipo_sanguineo")
    private String tipoSanguineo;

    @Column(name = "doencas_previas")
    private String doencasPrevias;

    @Column(name = "alergias")
    private String allergies;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
    
    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PacientModel() {

    }

    public PacientModel(LocalDate dateBirth, String tipoSanguineo, String doencasPrevias, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.dateBirth = dateBirth;
        this.tipoSanguineo = tipoSanguineo;
        this.doencasPrevias = doencasPrevias;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getDateBirth() {
        return dateBirth;
    }
    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }
    public String getTipoSanguineo() {
        return tipoSanguineo;
    }
    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }
    public String getDoencasPrevias() {
        return doencasPrevias;
    }
    public void setDoencasPrevias(String doencasPrevias) {
        this.doencasPrevias = doencasPrevias;
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

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }
}

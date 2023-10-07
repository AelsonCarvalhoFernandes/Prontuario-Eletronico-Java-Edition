package com.PI.ProntuarioEletronico.models.UserModels;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.PI.ProntuarioEletronico.resources.enums.Roles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users_pacient")
public class UserPacientModel {

    // Atributos dos pacientes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "id_user", nullable = false)
    private Long idUserModel;
    // @OneToMany
    // private Long idContact;
    // @OneToOne
    // private Long idContactEmergency;
    // @OneToOne
    // private Long idAddress;
    private LocalDate dateBirth;
    private boolean category; // true = ativo, false = inativo
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Get and Setters dos atributos
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUserModel() {
        return idUserModel;
    }

    public void setIdUserModel(Long idAuthUser) {
        this.idUserModel = idAuthUser;
    }

    /*
     * public Long getIdContact() {
     * return idContact;
     * }
     * 
     * public void setIdContact(Long idContact) {
     * this.idContact = idContact;
     * }
     * 
     * public Long getIdAddress() {
     * return idAddress;
     * }
     * 
     * public void setIdAddress(Long idAddress) {
     * this.idAddress = idAddress;
     * }
     */

    public String getCns() {
        return cns;
    }

    public void setCns(String cns) {
        this.cns = cns;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public boolean isCategory() {
        return category;
    }

    public void setCategory(boolean category) {
        this.category = category;
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
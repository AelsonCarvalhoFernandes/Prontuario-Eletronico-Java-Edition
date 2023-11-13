package com.pi.ProntuarioEletronico.models.prontuario;

import java.time.LocalDateTime;

//import java.util.List;

import com.pi.ProntuarioEletronico.models.user.typeUsers.DoctorModel;
import com.pi.ProntuarioEletronico.models.user.typeUsers.PacientModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "medical_prescription")
public class MedicalPrescriptionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pacient")
    private PacientModel patient;

    @ManyToOne
    @JoinColumn(name = "id_doctor")
    private DoctorModel doctor;

    @Column(name = "description", nullable = false)
    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public MedicalPrescriptionModel() {
    }

    public MedicalPrescriptionModel(Long id, PacientModel pacient, DoctorModel doctorName, String description,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.patient = pacient;
        this.doctor = doctorName;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PacientModel getPatient() {
        return patient;
    }

    public void setPatient(PacientModel patient) {
        this.patient = patient;
    }

    public DoctorModel getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorModel doctor) {
        this.doctor = doctor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    

    /*
     * public static List findAll() {
     * return null;
     * }
     */
}
package com.pi.ProntuarioEletronico.models.prontuario;

import com.pi.ProntuarioEletronico.models.user.typeUsers.DoctorModel;
import com.pi.ProntuarioEletronico.models.user.typeUsers.PacientModel;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "laudo")
public class LaudoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 500)
    private String description;
    @Column(length = 10000)
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "pacient_id")
    private PacientModel pacient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorModel doctor;

    public LaudoModel(String description, String note) {
        this.description = description;
        this.note = note;
    }

    public LaudoModel(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public PacientModel getPacient() {
        return pacient;
    }

    public void setPacient(PacientModel pacient) {
        this.pacient = pacient;
    }

    public DoctorModel getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorModel doctor) {
        this.doctor = doctor;
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

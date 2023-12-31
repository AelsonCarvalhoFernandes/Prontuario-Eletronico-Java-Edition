package com.pi.ProntuarioEletronico.models.user.typeUsers;

import java.time.LocalDateTime;

import com.pi.ProntuarioEletronico.models.user.UserModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "doctor")
public class DoctorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "crm", length = 15)
    @NotBlank
    private String crm;

    @Column(name = "formacao", length = 50)
    @NotBlank
    private String formacao;

    @Column(name = "cargo", length = 50)
    @NotBlank
    private String cargo;

    @Column(name = "dias_atendimento", length = 50)
    @NotBlank
    private String diasAtendimento;

    @Column(name = "horas_atendimento", length = 50)
    @NotBlank
    private String horasAtendimento;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private UserModel user;

    public DoctorModel() {
    }

    public DoctorModel(Long id, @NotBlank String formacao, @NotBlank String cargo, @NotBlank String diasAtendimento,
            @NotBlank String horasAtendimento, String crm, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.crm = crm;
        this.formacao = formacao;
        this.cargo = cargo;
        this.diasAtendimento = diasAtendimento;
        this.horasAtendimento = horasAtendimento;
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

    public String getDiasAtendimento() {
        return diasAtendimento;
    }

    public void setDiasAtendimento(String diasAtendimento) {
        this.diasAtendimento = diasAtendimento;
    }

    public String getHorasAtendimento() {
        return horasAtendimento;
    }

    public void setHorasAtendimento(String horasAtendimento) {
        this.horasAtendimento = horasAtendimento;
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

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

}

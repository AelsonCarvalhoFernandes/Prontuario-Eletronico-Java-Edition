package com.PI.ProntuarioEletronico.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "atestados")
public class AtestadoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private LocalDateTime dataAtualizacao;

    @Column(nullable = false)
    private String diagnostico;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel users;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private UserModel medico;

    public Long getId() {
        return id;
    }

    //public void setId(Long id) {this.id = id;}

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public UserModel getUsers() {
        return users;
    }

    public void setUsers(UserModel users) {
        this.users = users;
    }

    public UserModel getMedico() {
        return medico;
    }

    public void setMedico(UserModel medicos) {
        this.medico = medicos;
    }
}

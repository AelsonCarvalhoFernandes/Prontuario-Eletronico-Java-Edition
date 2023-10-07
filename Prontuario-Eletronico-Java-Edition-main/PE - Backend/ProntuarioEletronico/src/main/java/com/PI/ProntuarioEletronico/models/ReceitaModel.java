package com.PI.ProntuarioEletronico.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "receitas")
public class ReceitaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 255)
    private String resumo;
    @OneToMany(mappedBy = "receita")
    private List<ReceituarioModel> receituario;
    @Column(nullable = false)
    private LocalDateTime dataCriacao;
    @Column(nullable = false)
    private LocalDateTime dataAtualizacao;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel users;
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private UserModel medico;

    public List<ReceituarioModel> getReceituario() {
        return receituario;
    }

    public void setReceituario(List<ReceituarioModel> receituario) {
        this.receituario = receituario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

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

    public UserModel getUsers() {
        return users;
    }

    public void setUsers(UserModel users) {
        this.users = users;
    }

    public UserModel getMedico() {
        return medico;
    }

    public void setMedico(UserModel medico) {
        this.medico = medico;
    }

}

package com.PI.ProntuarioEletronico.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="laudo")
public class LaudoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String descricao;
    @NotBlank
    private String conteudo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel users;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private UserModel medico;

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


    @OneToMany(mappedBy = "laudo")
    private List<LaudoFilesModel> laudoFiles;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getDescricao() {
        return descricao;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public String getConteudo() {
        return conteudo;
    }


    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }


    public List<LaudoFilesModel> getLaudoFiles() {
        return laudoFiles;
    }


    public void setLaudoFiles(List<LaudoFilesModel> laudoFiles) {
        this.laudoFiles = laudoFiles;
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


    public LaudoModel() {
    }


    public LaudoModel(Long id, @NotBlank String descricao, @NotBlank String conteudo) {
        this.id = id;
        this.descricao = descricao;
        this.conteudo = conteudo;
    }
}

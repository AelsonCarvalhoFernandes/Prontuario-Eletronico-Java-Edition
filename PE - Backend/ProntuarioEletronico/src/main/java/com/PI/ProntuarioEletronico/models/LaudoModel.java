package com.PI.ProntuarioEletronico.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @OneToMany(mappedBy = "laudo")
    private List<LaudoFilesModel> laudoFiles;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    
    public LaudoModel() {
    }


    public LaudoModel(Long id, @NotBlank String descricao, @NotBlank String conteudo) {
        this.id = id;
        this.descricao = descricao;
        this.conteudo = conteudo;
    }
}

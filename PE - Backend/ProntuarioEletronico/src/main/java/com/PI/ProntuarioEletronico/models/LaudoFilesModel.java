package com.PI.ProntuarioEletronico.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "LaudoFiles")
public class LaudoFilesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String FileUrl;

    @ManyToOne
    @JoinColumn(name = "laudoId")
    private LaudoModel laudo;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

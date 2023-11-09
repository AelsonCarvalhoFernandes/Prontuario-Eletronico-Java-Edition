package com.pi.ProntuarioEletronico.models.prontuario;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "laudo_filepath")
public class FileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileurl;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "laudo_id")
    private LaudoModel laudo;

    public FileModel(String fileurl) {
        this.fileurl = fileurl;
    }

    public FileModel(){

    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public LaudoModel getLaudo() {
        return laudo;
    }

    public void setLaudo(LaudoModel laudo) {
        this.laudo = laudo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

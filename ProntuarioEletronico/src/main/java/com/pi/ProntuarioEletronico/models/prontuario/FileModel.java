package com.pi.ProntuarioEletronico.models.prontuario;

import com.pi.ProntuarioEletronico.models.user.typeUsers.PacientModel;
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
}

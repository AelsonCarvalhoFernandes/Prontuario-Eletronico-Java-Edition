package com.pi.ProntuarioEletronico.resources.dtos;

import com.pi.ProntuarioEletronico.models.prontuario.FileModel;
import com.pi.ProntuarioEletronico.models.prontuario.LaudoModel;

import java.util.List;

public class LaudoFullDataDto {

    public LaudoModel laudo;
    public List<FileModel> pdf;
    public List<FileModel> images;

    public LaudoFullDataDto(LaudoModel laudo, List<FileModel> pdf, List<FileModel> images){
        this.laudo = laudo;
        this.pdf = pdf;
        this.images = images;
    }

}

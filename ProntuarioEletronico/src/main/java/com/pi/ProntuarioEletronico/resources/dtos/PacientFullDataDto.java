package com.pi.ProntuarioEletronico.resources.dtos;

import com.pi.ProntuarioEletronico.models.prontuario.LaudoModel;
import com.pi.ProntuarioEletronico.models.user.UserModel;
import com.pi.ProntuarioEletronico.models.user.typeUsers.PacientModel;

import java.util.List;

public class PacientFullDataDto {
    public UserModel user;
    public PacientModel pacient;
    public List<LaudoModel> laudos;
    public PacientFullDataDto(UserModel user, PacientModel pacient) {
        this.user = user;
        this.pacient = pacient;
    }

    public List<LaudoModel> getLaudos() {
        return laudos;
    }

    public void setLaudos(List<LaudoModel> laudos) {
        this.laudos = laudos;
    }
}

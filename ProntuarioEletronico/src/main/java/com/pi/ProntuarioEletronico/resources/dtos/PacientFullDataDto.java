package com.pi.ProntuarioEletronico.resources.dtos;

import com.pi.ProntuarioEletronico.models.user.UserModel;
import com.pi.ProntuarioEletronico.models.user.typeUsers.PacientModel;

public class PacientFullDataDto {
    public UserModel user;
    public PacientModel pacient;
    public PacientFullDataDto(UserModel user, PacientModel pacient) {
        this.user = user;
        this.pacient = pacient;
    }
}

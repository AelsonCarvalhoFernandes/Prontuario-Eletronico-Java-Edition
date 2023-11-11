package com.pi.ProntuarioEletronico.resources.dtos;

public record DoctorDto(
        String firstName,
        String lastName,
        String email,
        String password,
        String rg,
        String cpf,
        String crm,
        String formacao,
        String cargo,
        String diasAtendimento,
        String horasAtendimento
) {
    
}

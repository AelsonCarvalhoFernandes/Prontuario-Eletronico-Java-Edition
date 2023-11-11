package com.pi.ProntuarioEletronico.resources.dtos;

public record CollaboratorDto(
        String firstName,
        String lastName,
        String email,
        String password,
        String rg,
        String cpf,
        String cns,
        String formacao,
        String cargo,
        String diasAtendimento,
        String horasAtendimento
) {
    
}

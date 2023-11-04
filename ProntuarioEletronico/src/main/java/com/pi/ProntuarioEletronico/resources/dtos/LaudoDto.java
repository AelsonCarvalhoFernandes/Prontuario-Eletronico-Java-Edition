package com.pi.ProntuarioEletronico.resources.dtos;

public record LaudoDto(
        String cnsPacient,
        String crmMedico,
        String cpf,
        String description,
        String note
) {
}

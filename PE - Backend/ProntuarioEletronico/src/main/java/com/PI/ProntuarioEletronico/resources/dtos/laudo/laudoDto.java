package com.PI.ProntuarioEletronico.resources.dtos.laudo;

import jakarta.validation.constraints.NotBlank;

public record laudoDto(
    String descricao,
    String conteudo,
    Long idMedico,
    Long idPaciente
) {
    
}

package com.PI.ProntuarioEletronico.resources.dtos.atestados;

public record AtestadoDTO(
        Long idMedico,
        Long idPaciente,
        String diagnostico
) { }

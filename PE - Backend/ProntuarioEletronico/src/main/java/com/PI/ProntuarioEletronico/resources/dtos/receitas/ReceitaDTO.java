package com.PI.ProntuarioEletronico.resources.dtos.receitas;

public record ReceitaDTO (
        String resumo,
        Long PacienteId,
        Long MedicoId
){ }

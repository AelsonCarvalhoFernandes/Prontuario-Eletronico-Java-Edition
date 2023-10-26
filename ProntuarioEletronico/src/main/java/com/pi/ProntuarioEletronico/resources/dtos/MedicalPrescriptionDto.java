package com.pi.ProntuarioEletronico.resources.dtos;

public record MedicalPrescriptionDto(
        Long idPacient,
        Long idDoctor,
        String description) {
}

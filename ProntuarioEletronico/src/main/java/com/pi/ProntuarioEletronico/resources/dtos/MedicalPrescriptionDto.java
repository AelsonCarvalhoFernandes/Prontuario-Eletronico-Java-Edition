package com.pi.ProntuarioEletronico.resources.dtos;

public record MedicalPrescriptionDto(
                String description,
                String cnsPacient,
                String crmMedico,
                String cpf) {
}

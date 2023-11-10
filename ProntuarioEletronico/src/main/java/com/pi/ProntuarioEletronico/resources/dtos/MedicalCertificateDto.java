package com.pi.ProntuarioEletronico.resources.dtos;

public record MedicalCertificateDto(
        String description,
        String createdAt,
        String updatedAt,
        String doctorName,
        String patientName) {
}

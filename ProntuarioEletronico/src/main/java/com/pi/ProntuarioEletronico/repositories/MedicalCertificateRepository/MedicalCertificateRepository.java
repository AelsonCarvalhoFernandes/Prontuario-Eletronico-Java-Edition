package com.pi.ProntuarioEletronico.repositories.MedicalCertificateRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pi.ProntuarioEletronico.models.prontuario.MedicalCertificateModel;

public interface MedicalCertificateRepository extends JpaRepository<MedicalCertificateModel, Long> {

}

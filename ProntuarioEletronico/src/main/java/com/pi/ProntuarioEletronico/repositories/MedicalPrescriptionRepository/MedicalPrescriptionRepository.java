package com.pi.ProntuarioEletronico.repositories.MedicalPrescriptionRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pi.ProntuarioEletronico.models.prontuario.MedicalPrescriptionModel;

public interface MedicalPrescriptionRepository extends JpaRepository<MedicalPrescriptionModel, Long> {

}

package com.pi.ProntuarioEletronico.repositories.MedicalPrescriptionRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pi.ProntuarioEletronico.models.MedicalPrescription.MedicalPrescriptionModel;

public interface MedicalPrescriptionRepository extends JpaRepository<MedicalPrescriptionModel, Long> {

}

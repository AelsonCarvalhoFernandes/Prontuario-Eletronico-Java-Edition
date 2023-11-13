package com.pi.ProntuarioEletronico.repositories.MedicalPrescriptionRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pi.ProntuarioEletronico.models.prontuario.MedicalPrescriptionModel;
import com.pi.ProntuarioEletronico.models.user.typeUsers.PacientModel;

public interface MedicalPrescriptionRepository extends JpaRepository<MedicalPrescriptionModel, Long> {

    List<MedicalPrescriptionModel> findByPatientId(Long patientId);

}

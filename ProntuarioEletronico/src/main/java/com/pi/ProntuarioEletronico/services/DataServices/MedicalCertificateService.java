package com.pi.ProntuarioEletronico.services.DataServices;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.ProntuarioEletronico.repositories.MedicalCertificateRepository.MedicalCertificateRepository;
import com.pi.ProntuarioEletronico.resources.dtos.MedicalCertificateDto;
import com.pi.ProntuarioEletronico.resources.dtos.MedicalPrescriptionDto;
import com.pi.ProntuarioEletronico.models.prontuario.MedicalCertificateModel;
import com.pi.ProntuarioEletronico.models.prontuario.MedicalPrescriptionModel;

@Service
public class MedicalCertificateService {

    @Autowired
    private MedicalCertificateRepository medicalCertificateRepository;

    /*
     * Metodos de busca dos atestados
     */

    public List<MedicalCertificateModel> listAll() {
        try {
            return medicalCertificateRepository.findAll();

        } catch (Exception ex) {
            throw new RuntimeException("Houve um error ao carregar os atestados: " + ex);
        }
    }

    /*
     * Metodos de busca dos atestados por {ID}
     */

    public MedicalCertificateModel findById(Long id) {
        try {
            Optional<MedicalCertificateModel> medicalPrescription = medicalCertificateRepository.findById(id);
            if (medicalPrescription.isEmpty()) {
                return null;
            }
            return medicalPrescription.get();

        } catch (Exception ex) {
            throw new RuntimeException("Houve um error ao carregar o atestado: " + ex);
        }
    }

    /*
     * Método de criação do atestado
     */

    public MedicalCertificateModel create(MedicalCertificateDto dto) {

        try {
            MedicalCertificateModel medicalCertificate = createMedicalCertificateFromDto(dto);
            medicalCertificateRepository.save(medicalCertificate);
            return medicalCertificate;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar atestado: " + e.getMessage(), e);
        }
    }

    public MedicalCertificateModel createMedicalCertificateFromDto(MedicalCertificateDto dto) {

        try {
            MedicalCertificateModel medicalCertificate = new MedicalCertificateModel();
            BeanUtils.copyProperties(dto, medicalCertificate);
            medicalCertificate.setPatient(medicalCertificate.getPatient());
            medicalCertificate.setDoctor(medicalCertificate.getDoctor());
            medicalCertificate.setCreatedAt(LocalDateTime.now());
            medicalCertificate.setUpdatedAt(LocalDateTime.now());
            return medicalCertificate;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar o atestado médico a partir do DTO: " + e.getMessage(), e);
            // return null;
        }
    }

    /*
     * Método de atualização dos atestados
     */

    public MedicalCertificateModel update(MedicalCertificateDto dto, Long id) {

        try {
            MedicalCertificateModel medicalCertificate = this.findById(id);
            if (medicalCertificate != null) {
                updateMedicalCertificateFromDto(dto, medicalCertificate);
                medicalCertificateRepository.save(medicalCertificate);
            }
            return medicalCertificate;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o atestado: " + e.getMessage(), e);
            // System.out.println("Erro ao atualizar receita: " + e.getMessage());
            // return null;
        }
    }

    public void updateMedicalCertificateFromDto(MedicalCertificateDto dto,
            MedicalCertificateModel medicalCertificate) {

        try {
            BeanUtils.copyProperties(dto, medicalCertificate);
            medicalCertificate.setUpdatedAt(LocalDateTime.now());

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o atestadi médica a partir do DTO: " + e.getMessage(), e);
            // return null;
        }
    }

    /*
     * Método de deleção de atestados
     */

    public boolean delete(Long id) {
        try {
            MedicalCertificateModel medicalCertificate = this.findById(id);
            if (medicalCertificate != null) {
                medicalCertificateRepository.delete(medicalCertificate);
                return true;
            }
            return false;

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao deletar atestado: " + ex.getMessage(), ex);
            // return false;
        }
    }
}
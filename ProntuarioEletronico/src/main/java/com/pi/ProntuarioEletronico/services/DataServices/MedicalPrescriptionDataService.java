package com.pi.ProntuarioEletronico.services.DataServices;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.ProntuarioEletronico.models.prontuario.MedicalPrescriptionModel;
import com.pi.ProntuarioEletronico.repositories.MedicalPrescriptionRepository.MedicalPrescriptionRepository;
import com.pi.ProntuarioEletronico.resources.dtos.MedicalPrescriptionDto;

@Service
public class MedicalPrescriptionDataService {

    @Autowired
    private MedicalPrescriptionRepository medicalPrescriptionRepository;

    @Autowired
    private PacientDataService pacientDataService;
    @Autowired
    private DoctorDataService doctorDataService;

    /*
     * Metodos de busca das receitas
     */

    public List<MedicalPrescriptionModel> listAll() {
        try {
            return medicalPrescriptionRepository.findAll();

        } catch (Exception ex) {
            throw new RuntimeException("Houve um error ao carregar as receitas: " + ex);
            // System.out.println("Houve um error ao carregar as receitas: " + ex);
            // return null;
        }
    }

    /*
     * Metodos de busca das receitas por {ID}
     */

    public MedicalPrescriptionModel findById(Long id) {
        try {
            Optional<MedicalPrescriptionModel> medicalPrescription = medicalPrescriptionRepository.findById(id);
            if (medicalPrescription.isEmpty()) {
                return null;
            }
            return medicalPrescription.get();

        } catch (Exception ex) {
            throw new RuntimeException("Houve um error ao carregar a receita: " + ex);
            // System.out.println("Houve um error ao carregar a receita: " + ex);
            // return null;
        }
    }

    public List<MedicalPrescriptionModel> findByPatientId(Long patientId) {
        try {

            return medicalPrescriptionRepository.findByPatientId(patientId);
        } catch (Exception ex) {
            throw new RuntimeException("Houve um erro ao buscar as receitas para o paciente: " + ex.getMessage(), ex);
        }
    }

    /*
     * Método de criação de receitas
     */

    public MedicalPrescriptionModel create(MedicalPrescriptionDto dto) {

        try {
            MedicalPrescriptionModel medicalPrescription = createMedicalPrescriptionFromDto(dto);
            medicalPrescriptionRepository.save(medicalPrescription);
            return medicalPrescription;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar receita: " + e.getMessage(), e);
            // System.out.println("Erro ao criar receita: " + e.getMessage());
            // return null;
        }
    }

    public MedicalPrescriptionModel createMedicalPrescriptionFromDto(MedicalPrescriptionDto dto) {

        try {
            MedicalPrescriptionModel medicalPrescription = new MedicalPrescriptionModel();
            BeanUtils.copyProperties(dto, medicalPrescription);

            medicalPrescription.setPatient(pacientDataService.findByCns(dto.cnsPacient()));
            medicalPrescription.setDoctor(doctorDataService.findByCrm(dto.crmMedico()));
            medicalPrescription.setCreatedAt(LocalDateTime.now());
            medicalPrescription.setUpdatedAt(LocalDateTime.now());
            return medicalPrescription;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar a prescrição médica a partir do DTO: " + e.getMessage(), e);
            // System.err.println("Erro ao criar a prescrição médica a partir do DTO: " +
            // e.getMessage());
            // return null;
        }
    }

    /*
     * Método de atualização de receitas
     */

    public MedicalPrescriptionModel update(MedicalPrescriptionDto dto, Long id) {

        try {
            MedicalPrescriptionModel medicalPrescription = this.findById(id);
            if (medicalPrescription != null) {
                updateMedicalPrescriptionFromDto(dto, medicalPrescription);
                medicalPrescriptionRepository.save(medicalPrescription);
            }
            return medicalPrescription;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar receita: " + e.getMessage(), e);
            // System.out.println("Erro ao atualizar receita: " + e.getMessage());
            // return null;
        }
    }

    public void updateMedicalPrescriptionFromDto(MedicalPrescriptionDto dto,
            MedicalPrescriptionModel medicalPrescription) {

        try {
            BeanUtils.copyProperties(dto, medicalPrescription);
            medicalPrescription.setUpdatedAt(LocalDateTime.now());
            // return medicalPrescription;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar a prescrição médica a partir do DTO: " + e.getMessage(), e);
            // System.err.println("Erro ao atualizar a prescrição médica a partir do DTO: "
            // + e.getMessage());
            // return null;
        }
    }

    /*
     * Método de deleção de receitas
     */

    public boolean delete(Long id) {
        try {
            MedicalPrescriptionModel medicalPrescription = this.findById(id);
            if (medicalPrescription != null) {
                medicalPrescriptionRepository.delete(medicalPrescription);
                return true;
            }
            return false;

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao deletar receita: " + ex.getMessage(), ex);
            // System.out.println("Error: " + ex.getMessage());
            // return false;
        }
    }
}
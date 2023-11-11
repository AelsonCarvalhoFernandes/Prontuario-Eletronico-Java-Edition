package com.pi.ProntuarioEletronico.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pi.ProntuarioEletronico.models.prontuario.MedicalPrescriptionModel;
import com.pi.ProntuarioEletronico.resources.dtos.MedicalPrescriptionDto;
import com.pi.ProntuarioEletronico.services.DataServices.MedicalPrescriptionDataService;

@Controller
@RequestMapping("prescriptions")
public class MedicalPrescriptionController {

    @Autowired
    private MedicalPrescriptionDataService medicalPrescriptionDataService;

    /*
     * listagem de receitas
     */
    @GetMapping("listMedicalPrescription")
    public ModelAndView listAll() {

        ModelAndView mv = new ModelAndView("ListMedicalPrescription");
        List<MedicalPrescriptionModel> medicalPrescriptions = medicalPrescriptionDataService.listAll();
        mv.addObject("medicalPrescriptions", medicalPrescriptions);

        return mv;
    }

    @GetMapping("MedicalPrescription/{id}")
    public ModelAndView list(@PathVariable(value = "id") Long id) {

        ModelAndView mv = new ModelAndView("MedicalPrescription");
        MedicalPrescriptionModel medicalPrescription = medicalPrescriptionDataService.findById(id);
        mv.addObject("medicalPrescription", medicalPrescription);
        return mv;
    }

    /*
     * Cadastro de receitas
     */
    @GetMapping("createMedicalPrescription")
    public ModelAndView create() {
        ModelAndView mv = new ModelAndView("Appointments/MedicalPrescriptions/CreatePrescriptions");

        return mv;
    }

    @PostMapping("create")
    public ModelAndView create(MedicalPrescriptionDto dto) {

        MedicalPrescriptionModel medicalPrescription = medicalPrescriptionDataService.create(dto);

        if (medicalPrescription != null) {
            return new ModelAndView("redirect:/prescriptions/listMedicalPrescription");
        }
        ModelAndView mv = new ModelAndView("redirect:/prescriptions/createMedicalPrescription");
        mv.addObject("error", "Houve um error ao Inserir a receita. confira os dados");

        return mv;
    }

    /*
     * Atualização de receitas
     */
    @GetMapping("updateMedicalPrescription/{id}")
    public ModelAndView update(@PathVariable(value = "id") Long id) {

        ModelAndView mv = new ModelAndView("UpdateMedicalPrescription");
        MedicalPrescriptionModel medicalPrescription = medicalPrescriptionDataService.findById(id);
        mv.addObject("medicalPrescription", medicalPrescription);

        return mv;
    }

    @PostMapping("updateMedicalPrescription/{id}")
    public ModelAndView update(@PathVariable(value = "id") Long id, MedicalPrescriptionDto dto) {

        MedicalPrescriptionModel medicalPrescription = medicalPrescriptionDataService.update(dto, id);

        if (medicalPrescription != null) {
            return new ModelAndView("redirect:/prescriptions/MedicalPrescription/" + id);
        }
        ModelAndView mv = new ModelAndView("redirect:/prescriptions/updateMedicalPrescription/" + id);
        mv.addObject("error", "Houve um error ao atualizar a receita. confira os dados");

        return mv;
    }

    /*
     * Deletar receitas
     */
    @DeleteMapping("deleteMedicalPrescription/{id}")
    public ModelAndView deleteMedicalPrescription(@PathVariable(value = "id") Long id) {

        medicalPrescriptionDataService.delete(id);
        ModelAndView mv = new ModelAndView("redirect:/prescriptions/listMedicalPrescription");

        return mv;
    }
}

package com.pi.ProntuarioEletronico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pi.ProntuarioEletronico.models.user.UserModel;
import com.pi.ProntuarioEletronico.resources.dtos.PacientDto;
import com.pi.ProntuarioEletronico.resources.dtos.PacientFullDataDto;
import com.pi.ProntuarioEletronico.services.DataServices.PacientDataService;

@Controller
@RequestMapping("doctor")
public class DoctorController {

    @Autowired
    private PacientDataService pacientDataService;

    /*
     * listagem de pacientes
     */
    @GetMapping("listPacient")
    public ModelAndView listAll(){
        ModelAndView mv = new ModelAndView("doctor/ListPacient");

        List<UserModel> pacients = pacientDataService.listAll();
        mv.addObject("pacients", pacients);

        return mv;
    }

    @GetMapping("paciente/{id}")
    public ModelAndView list(@PathVariable(value = "id") long id){
        ModelAndView mv = new ModelAndView("doctor/Pacient");

        PacientFullDataDto pacient = pacientDataService.pacientFullData(id);

        mv.addObject("user", pacient.user);
        mv.addObject("pacient", pacient.pacient);

        return mv;
    }
    
    /*
     * Cadastro de pacientes
     */
    @GetMapping("createPacient")
    public ModelAndView create(){
        ModelAndView mv = new ModelAndView("doctor/CreatePacient");

        return mv;
    }

    @PostMapping("create")
    public ModelAndView create(PacientDto dto){

        UserModel user = pacientDataService.create(dto);
        
        if(user != null){
            return new ModelAndView("redirect:listPacient");
        }
        ModelAndView mv = new ModelAndView("redirect:createPacient");
        mv.addObject("error", "Houve um error ao Inserir o paciente. confira os dados");

        return mv;
    }

    /*
     * 
     */
}

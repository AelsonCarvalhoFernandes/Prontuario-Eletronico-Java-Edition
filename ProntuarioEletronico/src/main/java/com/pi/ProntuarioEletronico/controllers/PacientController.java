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
import com.pi.ProntuarioEletronico.models.user.typeUsers.PacientModel;
import com.pi.ProntuarioEletronico.resources.dtos.PacientDto;
import com.pi.ProntuarioEletronico.resources.enums.Role;
import com.pi.ProntuarioEletronico.services.DataServices.PacientDataService;
import com.pi.ProntuarioEletronico.services.DataServices.UserDataService;

@Controller
@RequestMapping("pacient")
public class PacientController {

    @Autowired
    private PacientDataService pacientDataService;

    @Autowired
    private UserDataService userDataService;

    @GetMapping("all")
    public ModelAndView listAll(){

        List<UserModel> pacientes = userDataService.findByRole(Role.Pacient);

        ModelAndView mv = new ModelAndView("pacient/ListPacient");
 
        mv.addObject("pacients", pacientes);

        return mv;
    }

    @GetMapping("{id}")
    public ModelAndView pacient(@PathVariable(name = "id") Long id){

        UserModel pacientAccount = userDataService.findById(id);
        PacientModel pacientData = pacientDataService.findByUser(pacientAccount);

        ModelAndView mv = new ModelAndView("pacient/PacientData");

        mv.addObject("account", pacientAccount);
        mv.addObject("pacient", pacientData);

        return mv;
    }

    @GetMapping("create")
    public ModelAndView create(){

        ModelAndView mv = new ModelAndView("pacient/CreatePacient");

        return mv;
    }

    @PostMapping("create")
    public ModelAndView createNewPacient(PacientDto dto){

        PacientModel pacient = pacientDataService.create(dto);

        if(pacient == null){

            ModelAndView mv = new ModelAndView("pacient/CreatePacient");
            mv.addObject("Message", "Houve um erro ao cadastrar o paciente");
            return mv;
        }

        return new ModelAndView("redirect:pacient/all");
    }

    
    
}

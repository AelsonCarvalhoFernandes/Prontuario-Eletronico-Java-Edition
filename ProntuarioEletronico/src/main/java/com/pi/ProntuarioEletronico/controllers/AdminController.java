package com.pi.ProntuarioEletronico.controllers;

import com.pi.ProntuarioEletronico.models.user.typeUsers.DoctorModel;
import com.pi.ProntuarioEletronico.resources.dtos.DoctorDto;
import com.pi.ProntuarioEletronico.services.DataServices.DoctorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pi.ProntuarioEletronico.resources.dtos.PacientDto;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private DoctorDataService doctorDataService;
    
    /*
     *  Administradores
     */
    @GetMapping("createAdmin")
    public ModelAndView CreateAdminUser(){
        ModelAndView mv = new ModelAndView("admin/CreateAdmin");

        return mv;
    }

    @PostMapping("createAdmin")
    public void createAdminUser(PacientDto dto){
        
    }

    /*
     *  Medicos
     */
    @GetMapping("createDoctor")
    public ModelAndView createDoctor(){
        ModelAndView mv = new ModelAndView("admin/CreateDoctor");

        return mv;
    }

    @PostMapping("createDoctor/save")
    public ModelAndView createDoctor(DoctorDto dto){
        System.out.println("chegou aqui");
        DoctorModel doctor = doctorDataService.create(dto);

        if(doctor == null){
           return new ModelAndView("redirect:createDoctor");
        }

        return new ModelAndView("redirect:index");
    }

    /*
     *  Colaboradores
     */

    public ModelAndView createCollaborator(){
        ModelAndView mv = new ModelAndView("admin/CreateCollaborator");

        return mv;
    }
}

package com.pi.ProntuarioEletronico.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pi.ProntuarioEletronico.resources.dtos.PacientDto;

@Controller
@RequestMapping("admin")
public class AdminController {
    
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

    /*
     *  Colaboradores
     */

    public ModelAndView createCollaborator(){
        ModelAndView mv = new ModelAndView("admin/CreateCollaborator");

        return mv;
    }
}

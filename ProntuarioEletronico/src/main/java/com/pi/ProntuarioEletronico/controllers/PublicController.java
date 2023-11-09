package com.pi.ProntuarioEletronico.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pi.ProntuarioEletronico.services.DataServices.PacientDataService;

@Controller
public class PublicController {


    @Autowired
    private PacientDataService pacientDataService;

    @GetMapping("index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("public/index");

        return mv;
    }
    @GetMapping("about")
    public ModelAndView about(){
        ModelAndView mv = new ModelAndView("public/about");

        return mv;
    }

    @GetMapping("login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("public/login");

        return mv;
    }

    @PostMapping("authenticate")
    public ModelAndView authenticate(){
        return new ModelAndView();
    }

    @GetMapping("teste")
    public ModelAndView teste(){

        ModelAndView mv = new ModelAndView("public/teste");

        mv.addObject("pacients", pacientDataService.listAll());

        return mv;

    }
}

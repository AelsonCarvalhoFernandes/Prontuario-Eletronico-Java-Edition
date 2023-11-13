package com.pi.ProntuarioEletronico.controllers;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.pi.ProntuarioEletronico.services.DataServices.PacientDataService;

@Controller
public class PublicController {

    @Autowired
    private PacientDataService pacientDataService;

    @GetMapping({ "/", "index" })
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("public/index");

        return mv;
    }

    @GetMapping("about")
    public ModelAndView about() {
        ModelAndView mv = new ModelAndView("public/about");

        return mv;
    }

    @GetMapping("login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("public/login");

        return mv;
    }

    @PostMapping("authenticate")
    public ModelAndView authenticate(@RequestParam String email, @RequestParam String password) {
        return new ModelAndView();
    }

    @GetMapping("/home")
    public ModelAndView acessed() {
        ModelAndView mv = new ModelAndView("prontuario/home");

        return mv;
    }

    @GetMapping("teste")
    public ModelAndView teste() {

        ModelAndView mv = new ModelAndView("prontuario/teste");

        mv.addObject("pacients", pacientDataService.listAll());

        return mv;

    }

}
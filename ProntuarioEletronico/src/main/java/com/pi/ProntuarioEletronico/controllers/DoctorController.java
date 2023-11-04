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
import com.pi.ProntuarioEletronico.models.user.typeUsers.DoctorModel;
import com.pi.ProntuarioEletronico.resources.dtos.DoctorDto;
import com.pi.ProntuarioEletronico.resources.enums.Role;
import com.pi.ProntuarioEletronico.services.DataServices.DoctorDataService;
import com.pi.ProntuarioEletronico.services.DataServices.UserDataService;

@Controller
@RequestMapping("doctor")
public class DoctorController {

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private DoctorDataService doctorDataService;

    @GetMapping("all")
    public ModelAndView doctos(){

        List<UserModel> doctorAccounts = userDataService.findByRole(Role.Doctor);
        ModelAndView mv = new ModelAndView("doctor/ListDoctors");
        mv.addObject("doctors", doctorAccounts);

        return mv;
    }

    @GetMapping("{id}")
    public ModelAndView doctor(@PathVariable(name = "id") Long id){

        UserModel doctorAccount = userDataService.findById(id);
        DoctorModel doctorData = doctorDataService.findByUser(doctorAccount);

        ModelAndView mv = new ModelAndView("doctor/DoctorData");

        mv.addObject("account", doctorAccount);
        mv.addObject("data", doctorData);

        return mv;
    }

    @GetMapping("create")
    public ModelAndView create(){

        ModelAndView mv = new ModelAndView("doctor/CreateDoctor");
        return mv;
    }

    @PostMapping("create")
    public ModelAndView createNewDoctor(DoctorDto dto){

        DoctorModel doctor = doctorDataService.create(dto);

        if(doctor == null){
            ModelAndView mv = new ModelAndView("doctor/CreateDoctor");
            mv.addObject("Message", "Houve um erro ao cadastrar o médico");
            return mv;
        }

        return new ModelAndView("redirect:doctor/all");
    }

    @GetMapping("update/{id}")
    public ModelAndView update(@PathVariable(name = "id") Long id){

        UserModel doctorAccount = userDataService.findById(id);
        DoctorModel doctorData = doctorDataService.findByUser(doctorAccount);

        ModelAndView mv = new ModelAndView("doctor/UpdateDoctor");

        mv.addObject("account", doctorAccount);
        mv.addObject("data", doctorData);

        return mv;
    }

    @PostMapping("delete/{id}")
    public ModelAndView delete(@PathVariable(name = "id") Long id){
        boolean deleted = doctorDataService.delete(id);

        if(deleted == false){
            ModelAndView mv = new ModelAndView("doctor/"+id);
            mv.addObject("Message", "Houve um erro ao deletar o médico");
            return mv;
        }

        return new ModelAndView("redirect:doctor/all");

    }
}

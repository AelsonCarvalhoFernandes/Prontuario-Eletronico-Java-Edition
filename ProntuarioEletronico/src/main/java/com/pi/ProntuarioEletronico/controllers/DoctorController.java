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
import com.pi.ProntuarioEletronico.models.user.contactUsers.ContactModel;
import com.pi.ProntuarioEletronico.models.user.typeUsers.DoctorModel;
import com.pi.ProntuarioEletronico.resources.dtos.DoctorDto;
import com.pi.ProntuarioEletronico.resources.enums.Role;
import com.pi.ProntuarioEletronico.services.DataServices.ContactDataService;
import com.pi.ProntuarioEletronico.services.DataServices.DoctorDataService;
import com.pi.ProntuarioEletronico.services.DataServices.UserDataService;

@Controller
@RequestMapping("doctor")
public class DoctorController {

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private DoctorDataService doctorDataService;

    @Autowired
    private ContactDataService contactDataService;

    @GetMapping("all")
    public ModelAndView doctos() {

        List<UserModel> doctorAccounts = userDataService.findByRole(Role.Doctor);
        ModelAndView mv = new ModelAndView("doctor/ListDoctors");
        mv.addObject("doctors", doctorAccounts);

        return mv;
    }

    @GetMapping("{id}")
    public ModelAndView doctor(@PathVariable(name = "id") Long id) {

        UserModel account = userDataService.findById(id);
        DoctorModel doctorData = doctorDataService.findByUser(account);
        ContactModel contact = contactDataService.findByUser(account);

        ModelAndView mv = new ModelAndView("doctor/DoctorData");

        mv.addObject("account", account);
        mv.addObject("data", doctorData);
        mv.addObject("contact", contact);

        return mv;
    }

    @GetMapping("create")
    public ModelAndView create() {

        ModelAndView mv = new ModelAndView("doctor/CreateDoctor");
        return mv;
    }

    @PostMapping("create")
    public ModelAndView createNewDoctor(DoctorDto dto) {

        DoctorModel doctor = doctorDataService.create(dto);

        if (doctor == null) {
            ModelAndView mv = new ModelAndView("doctor/CreateDoctor");
            mv.addObject("Message", "Houve um erro ao cadastrar o médico");
            return mv;
        }

        return new ModelAndView("redirect:all");
    }

    @GetMapping("update/{id}")
    public ModelAndView update(@PathVariable(name = "id") Long id) {

        UserModel account = userDataService.findById(id);
        DoctorModel doctorData = doctorDataService.findByUser(account);
        ContactModel contact = contactDataService.findByUser(account);

        ModelAndView mv = new ModelAndView("doctor/UpdateDoctor");

        mv.addObject("account", account);
        mv.addObject("data", doctorData);
        mv.addObject("contact", contact);

        return mv;
    }

    @PostMapping("update/{id}")
    public ModelAndView updateDoctor(@PathVariable(name = "id") Long id, DoctorDto dto) {
        DoctorModel doctor = doctorDataService.update(dto, id);

        if (doctor == null) {
            return new ModelAndView("redirect:error");
        }

        return new ModelAndView("redirect:doctor/all");

    }

    @PostMapping("delete/{id}")
    public ModelAndView delete(@PathVariable(name = "id") Long id) {
        boolean deleted = doctorDataService.delete(id);

        if (deleted == false) {
            ModelAndView mv = new ModelAndView("doctor/" + id);
            mv.addObject("Message", "Houve um erro ao deletar o médico");
            return mv;
        }

        return new ModelAndView("redirect:doctor/all");
    }
}

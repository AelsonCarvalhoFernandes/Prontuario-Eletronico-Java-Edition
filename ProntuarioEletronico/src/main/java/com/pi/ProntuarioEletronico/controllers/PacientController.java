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
import com.pi.ProntuarioEletronico.models.user.typeUsers.PacientModel;
import com.pi.ProntuarioEletronico.resources.dtos.PacientDto;
import com.pi.ProntuarioEletronico.resources.dtos.PacientUpdateDto;
import com.pi.ProntuarioEletronico.resources.enums.Role;
import com.pi.ProntuarioEletronico.services.DataServices.ContactDataService;
import com.pi.ProntuarioEletronico.services.DataServices.PacientDataService;
import com.pi.ProntuarioEletronico.services.DataServices.UserDataService;

@Controller
@RequestMapping("pacient")
public class PacientController {

    @Autowired
    private PacientDataService pacientDataService;

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private ContactDataService contactDataService;

    @GetMapping("all")
    public ModelAndView listAll() {

        List<UserModel> pacientes = userDataService.findByRole(Role.Pacient);

        ModelAndView mv = new ModelAndView("pacient/ListPacient");

        mv.addObject("pacients", pacientes);

        return mv;
    }

    @GetMapping("{id}")
    public ModelAndView pacient(@PathVariable(name = "id") Long id) {

        UserModel account = userDataService.findById(id);
        PacientModel pacientData = pacientDataService.findByUser(account);
        ContactModel contact = contactDataService.findByUser(account);

        ModelAndView mv = new ModelAndView("pacient/PacientData");

        mv.addObject("account", account);
        mv.addObject("pacient", pacientData);
        mv.addObject("contact", contact);

        return mv;
    }

    @GetMapping("create")
    public ModelAndView create() {

        ModelAndView mv = new ModelAndView("pacient/CreatePacient");

        return mv;
    }

    @PostMapping("create")
    public ModelAndView createNewPacient(PacientDto dto) {

        PacientModel pacient = pacientDataService.create(dto);

        if (pacient == null) {

            ModelAndView mv = new ModelAndView("pacient/CreatePacient");
            mv.addObject("Message", "Houve um erro ao cadastrar o paciente");
            return mv;
        }

        return new ModelAndView("redirect:/pacient/all");
    }

    @GetMapping("update/{id}")
    public ModelAndView update(@PathVariable(name = "id") Long id) {

        UserModel account = userDataService.findById(id);
        if (account == null) {
            // Lide com a situação em que o usuário não foi encontrado, redirecione ou
            // retorne uma mensagem de erro.
            // Exemplo de redirecionamento para uma página de erro:
            return new ModelAndView("redirect:/error");
        }
        PacientModel pacientData = pacientDataService.findByUser(account);
        ContactModel contact = contactDataService.findByUser(account);

        ModelAndView mv = new ModelAndView("pacient/UpdatePacient");

        mv.addObject("account", account);
        mv.addObject("data", pacientData);
        mv.addObject("contact", contact);

        return mv;
    }

    @PostMapping("update/{id}")
    public ModelAndView updatePacient(@PathVariable(name = "id") Long id, PacientDto dto) {
        Long idP = Long.parseLong(dto.id());
        PacientModel pacient = pacientDataService.update(dto, idP);

        if (pacient == null) {

            ModelAndView mv = new ModelAndView("pacient/UpdatePacient");
            mv.addObject("Message", "Houve um erro ao atualizar o paciente");
            return mv;
        }
        return new ModelAndView("redirect:/pacient/all");
    }

    @PostMapping("delete/{id}")
    public ModelAndView delete(@PathVariable(name = "id") Long id) {
        boolean deleted = pacientDataService.delete(id);

        if (deleted == false) {
            return new ModelAndView("redirect:error");
        }

        return new ModelAndView("redirect:pacient/all");
    }

}

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
import com.pi.ProntuarioEletronico.models.user.typeUsers.CollaboratorModel;
import com.pi.ProntuarioEletronico.resources.dtos.CollaboratorDto;
import com.pi.ProntuarioEletronico.resources.enums.Role;
import com.pi.ProntuarioEletronico.services.DataServices.CollaboratorDataService;
import com.pi.ProntuarioEletronico.services.DataServices.ContactDataService;
import com.pi.ProntuarioEletronico.services.DataServices.UserDataService;

@Controller
@RequestMapping("collaborator")
public class CollaboratorController {

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private CollaboratorDataService collaboratorDataService;

    @Autowired ContactDataService contactDataService;

    @GetMapping("all")
    public ModelAndView collaborators(){

        List<UserModel> accounts = userDataService.findByRole(Role.Collaborator);

        ModelAndView mv = new ModelAndView("collaborator/ListCollaborator");
        mv.addObject("collaborators", accounts);

        return mv;
    } 

    @GetMapping("{id}")
    public ModelAndView collaborator(@PathVariable(name = "id") Long id){
        UserModel account = userDataService.findById(id);
        CollaboratorModel data = collaboratorDataService.findByUser(account);
        ContactModel contact = contactDataService.findByUser(account);

        ModelAndView mv = new ModelAndView("collaborator/Collaborator");

        mv.addObject("account", account);
        mv.addObject("data", data);
        mv.addObject("contact", contact);

        return mv;
    }

    @GetMapping("create")
    public ModelAndView create(){

        ModelAndView mv = new ModelAndView("collaborator/CreateCollaborator");
        return mv;

    }

    @PostMapping("create")
    public ModelAndView createNewCollaborator(CollaboratorDto dto){
        CollaboratorModel collaborator = collaboratorDataService.create(dto);

        if(collaborator == null){
            ModelAndView mv = new ModelAndView("collaborator/CreateCollaborator");
            mv.addObject("Message", "Houve um erro ao cadastrar o colaborador");
            return mv;
        }

        return new ModelAndView("redirect:collaborator/all");
    }

    @GetMapping("update/{id}")
    public ModelAndView update(@PathVariable(name = "id") Long id){
        UserModel account = userDataService.findById(id);
        CollaboratorModel data = collaboratorDataService.findByUser(account);
        ContactModel contact = contactDataService.findByUser(account);

        ModelAndView mv = new ModelAndView("collaborator/UpdateCollaborator");

        mv.addObject("account", account);
        mv.addObject("data", data);
        mv.addObject("contact", contact);

        return mv;
    }

    @PostMapping("update/{id}")
    public ModelAndView updateCollaborator(@PathVariable(name = "id") Long id, CollaboratorDto dto){

        CollaboratorModel collaborator = collaboratorDataService.update(id, dto);

        if(collaborator == null){
            ModelAndView mv = new ModelAndView("collaborator/Collaborator");
            mv.addObject("Message", "Houve um erro ao atualizar o colaborador");
            return mv;
        }

        return new ModelAndView("redirect:collaborator/"+id);

    }

    @PostMapping("delete/{id}")
    public ModelAndView delete(@PathVariable(name = "id") Long id){

        boolean deleted = collaboratorDataService.delete(id);

        if(deleted == false){
            ModelAndView mv = new ModelAndView("error/Error");
            mv.addObject("Message", "Houve um erro ao atualizar o colaborador");
            return mv;
        }

        return new ModelAndView("redirect:collaborator/all");

    }
}

package com.pi.ProntuarioEletronico.services.DataServices;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pi.ProntuarioEletronico.models.user.UserModel;
import com.pi.ProntuarioEletronico.models.user.contactUsers.ContactModel;
import com.pi.ProntuarioEletronico.models.user.typeUsers.CollaboratorModel;
import com.pi.ProntuarioEletronico.repositories.UserRepository.ICollaboratorRepository;
import com.pi.ProntuarioEletronico.resources.dtos.CollaboratorDto;
import com.pi.ProntuarioEletronico.resources.enums.Role;

@Service
public class CollaboratorDataService {

    @Autowired
    private ICollaboratorRepository collaboratorRepository;

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private ContactDataService contactDataService;

    public List<CollaboratorModel> listAll() {
        try {

            return collaboratorRepository.findAll();

        } catch (Exception ex) {

            System.out.println("Error: " + ex.getMessage());
            return null;

        }
    }

    public CollaboratorModel findByUser(UserModel user) {
        try {

            CollaboratorModel collaborator = collaboratorRepository.findByUser(user);

            return collaborator;

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    public CollaboratorModel create(CollaboratorDto dto) {
        try {

            UserModel user = userDataService.findByCpf(dto.cpf());
            if (user != null) {
                return null;
            }
            user = new UserModel();
            BeanUtils.copyProperties(dto, user);

            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user.setRole(Role.Collaborator);
            user = userDataService.create(user);

            CollaboratorModel collaborator = new CollaboratorModel();
            BeanUtils.copyProperties(dto, collaborator);

            collaborator.setCreatedAt(LocalDateTime.now());
            collaborator.setUpdatedAt(LocalDateTime.now());
            collaboratorRepository.save(collaborator);

            ContactModel contact = new ContactModel();
            BeanUtils.copyProperties(dto, contact);
            contact.setUser(user);

            contactDataService.create(contact);

            return collaborator;

        } catch (Exception ex) {

            System.out.println("Error: " + ex.getMessage());
            return null;

        }
    }

    public CollaboratorModel update(Long id, CollaboratorDto dto) {
        try {
            UserModel user = userDataService.findById(id);
            if (user == null) {
                return null;
            }

            BeanUtils.copyProperties(dto, user);
            user = userDataService.update(user);

            CollaboratorModel collaborator = collaboratorRepository.findByUser(user);
            BeanUtils.copyProperties(dto, collaborator);

            collaboratorRepository.save(collaborator);

            ContactModel contact = new ContactModel();
            BeanUtils.copyProperties(dto, contact);
            contact.setUser(user);

            contactDataService.create(contact);

            return collaborator;

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    public boolean delete(Long id) {
        try {
            UserModel user = userDataService.findById(id);
            if (user == null) {
                return false;
            }

            CollaboratorModel collaborator = collaboratorRepository.findByUser(user);

            collaboratorRepository.delete(collaborator);

            return true;

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return false;
        }
    }
}

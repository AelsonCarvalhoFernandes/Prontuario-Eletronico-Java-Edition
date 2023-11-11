package com.pi.ProntuarioEletronico.services.DataServices;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pi.ProntuarioEletronico.models.user.UserModel;
import com.pi.ProntuarioEletronico.models.user.contactUsers.ContactModel;
import com.pi.ProntuarioEletronico.models.user.typeUsers.PacientModel;

import com.pi.ProntuarioEletronico.repositories.UserRepository.IPacientRepository;

import com.pi.ProntuarioEletronico.resources.dtos.PacientDto;
import com.pi.ProntuarioEletronico.resources.dtos.PacientFullDataDto;
import com.pi.ProntuarioEletronico.resources.dtos.PacientUpdateDto;
import com.pi.ProntuarioEletronico.resources.enums.Role;

@Service
public class PacientDataService {

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private IPacientRepository pacientRepository;

    @Autowired
    private ContactDataService contactDataService;

    /*
     * Metodos de busca dos pacientes
     */

    public List<PacientModel> listAll() {
        try {
            return pacientRepository.findAll();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    public UserModel findById(Long id) {
        try {
            UserModel user = userDataService.findById(id);
            return user;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    public PacientModel findByUser(UserModel user) {
        try {
            PacientModel pacient = pacientRepository.findByUser(user);
            return pacient;

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    public PacientModel findByCns(String cns) {
        try {

            PacientModel pacient = pacientRepository.findByCns(cns);
            return pacient;

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    public UserModel findByNameOrCpf(String data) {
        try {
            UserModel user = userDataService.findByCpf(data);
            return user;

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    public PacientFullDataDto pacientFullData(Long id) {
        try {
            UserModel user = userDataService.findById(id);
            if (user != null) {
                PacientModel pacient = pacientRepository.findByUser(user);

                PacientFullDataDto data = new PacientFullDataDto(user, pacient);

                // data.setLaudos(laudoDataService.findByPacient(pacient));
                return data;
            }

            return null;

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    /*
     * metodos para criar um paciente
     */

    public PacientModel create(PacientDto dto) {

        try {
            System.out.println("Chegou aqui 2");
            UserModel user = new UserModel();

            BeanUtils.copyProperties(dto, user);

            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user.setRole(Role.Pacient);

            user = userDataService.create(user);

            PacientModel paciente = new PacientModel();
            paciente.setUser(user);

            BeanUtils.copyProperties(dto, paciente);

            paciente.setCreatedAt(LocalDateTime.now());
            paciente.setUpdatedAt(LocalDateTime.now());

            pacientRepository.save(paciente);

            ContactModel contact = new ContactModel();
            BeanUtils.copyProperties(dto, contact);
            contact.setUser(user);

            contactDataService.create(contact);

            return paciente;

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    /*
     * metodo para atualizar o paciente
     */

    public PacientModel update(PacientUpdateDto dto, Long Id) {
        try {
            UserModel user = userDataService.findById(Id);

            BeanUtils.copyProperties(dto, user);

            user = userDataService.update(user);

            PacientModel pacient = pacientRepository.findByUser(user);

            BeanUtils.copyProperties(dto, pacient);

            pacient.setUpdatedAt(LocalDateTime.now());

            pacientRepository.save(pacient);

            ContactModel contact = new ContactModel();
            BeanUtils.copyProperties(dto, contact);
            contact.setUser(user);

            contactDataService.create(contact);

            return pacient;

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    /*
     * Metodos para deletar o paciente
     */

    public boolean delete(Long id) {
        try {
            UserModel user = userDataService.findById(id);

            if (user == null) {
                return false;
            }

            PacientModel pacient = this.findByUser(user);
            pacientRepository.delete(pacient);
            userDataService.delete(id);

            return true;

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return false;
        }
    }
}

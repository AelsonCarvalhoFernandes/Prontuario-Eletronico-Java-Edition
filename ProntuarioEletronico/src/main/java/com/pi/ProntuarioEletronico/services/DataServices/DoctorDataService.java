package com.pi.ProntuarioEletronico.services.DataServices;

import com.pi.ProntuarioEletronico.models.user.UserModel;
import com.pi.ProntuarioEletronico.models.user.contactUsers.ContactModel;
import com.pi.ProntuarioEletronico.models.user.typeUsers.DoctorModel;
import com.pi.ProntuarioEletronico.repositories.UserRepository.IDoctorRepository;
import com.pi.ProntuarioEletronico.resources.dtos.DoctorDto;
import com.pi.ProntuarioEletronico.resources.enums.Role;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DoctorDataService {

    @Autowired
    private IDoctorRepository doctorRepository;

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private ContactDataService contactDataService;

    public DoctorModel create(DoctorDto dto){
        try{
        System.out.println("Chegou aqui!!");
            UserModel user = new UserModel();
            user.setRole(Role.Doctor);
            BeanUtils.copyProperties(dto, user);

            DoctorModel doctor = new DoctorModel();
            BeanUtils.copyProperties(dto, doctor);
            doctor.setCreatedAt(LocalDateTime.now());
            doctor.setUpdatedAt(LocalDateTime.now());

            user = userDataService.create(user);

            doctor.setUser(user);
            doctorRepository.save(doctor);

            ContactModel contact = new ContactModel();
            BeanUtils.copyProperties(dto, contact);
            contact.setUser(user);

            contactDataService.create(contact);

            return doctor;

        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    public DoctorModel findbyId(Long id){
        try{
            UserModel user = userDataService.findById(id);
            if(user == null){
                return null;
            }

            DoctorModel doctor = doctorRepository.findByUser(user);

            return doctor;

        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    public DoctorModel findByUser(UserModel user){
        try{
            DoctorModel doctor = doctorRepository.findByUser(user);
            return doctor;

        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
            
        }
    }

    public DoctorModel findByCrm(String crm){
        try{
            DoctorModel doctor = doctorRepository.findByCrm(crm);
            return doctor;

        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    public DoctorModel update(DoctorDto dto, Long id){
        try{
            UserModel user = userDataService.findById(id);
            BeanUtils.copyProperties(dto, user);

            userDataService.create(user);

            DoctorModel doctor = doctorRepository.findByUser(user);
            BeanUtils.copyProperties(dto, doctor);
            doctor.setUpdatedAt(LocalDateTime.now());

            doctorRepository.save(doctor);

            ContactModel contact = new ContactModel();
            BeanUtils.copyProperties(dto, contact);
            contact.setUser(user);

            contactDataService.create(contact);

            return doctor;

        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    public boolean delete(Long id){
        try{

            UserModel user = userDataService.findById(id);

            DoctorModel doctor = doctorRepository.findByUser(user);

            doctorRepository.delete(doctor);

            userDataService.delete(id);

            return true;

        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return false;
        }
    }
}

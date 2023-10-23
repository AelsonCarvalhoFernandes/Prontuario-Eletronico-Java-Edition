package com.pi.ProntuarioEletronico.services.DataServices;

import com.pi.ProntuarioEletronico.models.user.UserModel;
import com.pi.ProntuarioEletronico.models.user.typeUsers.DoctorModel;
import com.pi.ProntuarioEletronico.repositories.UserRepository.IDoctorRepository;
import com.pi.ProntuarioEletronico.repositories.UserRepository.IUserRepository;
import com.pi.ProntuarioEletronico.resources.dtos.DoctorDto;
import com.pi.ProntuarioEletronico.resources.enums.Role;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DoctorDataService {

    @Autowired
    private IDoctorRepository doctorRepository;

    @Autowired
    private IUserRepository userRepository;

    public UserModel create(DoctorDto dto){
        try{
            UserModel user = new UserModel();

            user.setRole(Role.Doctor);

            BeanUtils.copyProperties(dto, user);

            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());

            DoctorModel doctor = new DoctorModel();

            BeanUtils.copyProperties(dto, doctor);

            doctor.setCreatedAt(LocalDateTime.now());
            doctor.setUpdatedAt(LocalDateTime.now());

            user = userRepository.save(user);

            doctor.setUser(user);

            doctorRepository.save(doctor);

            return user;

        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    public UserModel findbyId(Long id){
        try{
            Optional<UserModel> user = userRepository.findById(id);
            if(user.get() == null){
                return null;
            }

            return user.get();

        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    public UserModel update(DoctorDto dto, Long id){
        try{
            UserModel user = findbyId(id);

            BeanUtils.copyProperties(dto, user);
            user.setUpdatedAt(LocalDateTime.now());

            userRepository.save(user);

            DoctorModel doctor = doctorRepository.findByUser(user);

            BeanUtils.copyProperties(dto, doctor);
            doctor.setUpdatedAt(LocalDateTime.now());

            doctorRepository.save(doctor);

            return user;

        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
}

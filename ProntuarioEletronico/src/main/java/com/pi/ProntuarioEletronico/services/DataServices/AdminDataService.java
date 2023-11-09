package com.pi.ProntuarioEletronico.services.DataServices;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.ProntuarioEletronico.models.user.UserModel;
import com.pi.ProntuarioEletronico.repositories.UserRepository.IUserRepository;
import com.pi.ProntuarioEletronico.resources.dtos.AdminDto;
import com.pi.ProntuarioEletronico.resources.enums.Role;

@Service
public class AdminDataService {
    
    @Autowired
    private IUserRepository userRepository;

    public UserModel create(AdminDto dto){
        try{
            UserModel user = new UserModel();

            BeanUtils.copyProperties(dto, user);

            user.setRole(Role.Administrator);

            user.setRole(Role.Administrator);

            userRepository.save(user);
            
            return user;

        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
            
        }
    }
}

package com.pi.ProntuarioEletronico.services.DataServices;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.ProntuarioEletronico.models.user.UserModel;
import com.pi.ProntuarioEletronico.models.user.typeUsers.PacientModel;
import com.pi.ProntuarioEletronico.repositories.UserRepository.IPacientRepository;
import com.pi.ProntuarioEletronico.repositories.UserRepository.IUserRepository;
import com.pi.ProntuarioEletronico.resources.dtos.PacientDto;
import com.pi.ProntuarioEletronico.resources.dtos.PacientFullDataDto;

@Service
public class PacientDataService {
    
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPacientRepository pacientRepository;

    /*
     *  Metodos de busca dos pacientes
     */

    public List<UserModel> listAll(){
        try{
            return userRepository.findAll();
        }catch(Exception ex){
            System.out.println("Error: "+ ex.getMessage());
            return null;
        }
    }

    public UserModel findById(Long id){
        try{
            Optional<UserModel> user = userRepository.findById(id);
            return user.get();
        }catch(Exception ex){
            System.out.println("Error: "+ ex.getMessage());
            return null;
        }
    }

    public List<UserModel> findByNameOrCpf(String data){
        try {
            List<UserModel> users = userRepository.findByCpf(data);

            if(users == null){
                users = userRepository.findByFirstName(data);
            }

            return users;

        } catch (Exception ex) {
            System.out.println("Error: "+ ex.getMessage());
            return null;
        }
    }

    public PacientFullDataDto pacientFullData(Long id){
        try{
            Optional<UserModel> user = userRepository.findById(id);
            if(user.get() != null){
                PacientModel pacient = pacientRepository.findByUser(user.get());

                PacientFullDataDto data = new PacientFullDataDto(user.get(), pacient);
                return data;
            }

            return null;

        }catch(Exception ex){
            System.out.println("Error: "+ ex.getMessage());
            return null;
        }
    }
    /*
     * metodos para criar um paciente
     */

    public UserModel create(PacientDto dto){
        
        try{
            UserModel user = new UserModel();

            BeanUtils.copyProperties(dto, user);

            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());

            userRepository.save(user);

            PacientModel paciente = new PacientModel();
            paciente.setUser(user);

            BeanUtils.copyProperties(dto, paciente);

            paciente.setCreatedAt(LocalDateTime.now());
            paciente.setUpdatedAt(LocalDateTime.now());

            pacientRepository.save(paciente);

            return user;

        }catch(Exception ex){
            System.out.println("Error: "+ ex.getMessage());
            return null;
        }
    }

    /*
     * metodo para atualizar o paciente
     */

    public UserModel update(PacientDto dto, Long Id){
        try{
            UserModel user = this.findById(Id);

            BeanUtils.copyProperties(dto, user);

            user.setUpdatedAt(LocalDateTime.now());

            userRepository.save(user);

            PacientModel pacient = pacientRepository.findByUser(user);

            BeanUtils.copyProperties(dto, pacient);

            pacient.setUpdatedAt(LocalDateTime.now());

            pacientRepository.save(pacient);

            return user;
            
        }catch(Exception ex){
            System.out.println("Error: "+ ex.getMessage());
            return null;
        }
    }

    /*
     * Metodos para deletar o paciente
     */

    public boolean delete(Long id){
        try{
            UserModel user = this.findById(id);
            if(user != null){
                userRepository.delete(user);
                return true;
            }

            return false;

        }catch(Exception ex){
            System.out.println("Error: "+ ex.getMessage());
            return false;
        }
    }
}

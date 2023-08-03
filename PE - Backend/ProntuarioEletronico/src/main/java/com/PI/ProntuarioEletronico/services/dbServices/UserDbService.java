package com.PI.ProntuarioEletronico.services.dbServices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PI.ProntuarioEletronico.models.UserModel;
import com.PI.ProntuarioEletronico.repositories.UserRepository;

/*
 * Classe responsável para manipulação e persistencia de usuários no banco de dados
 */

@Service
public class UserDbService {

    @Autowired
    private UserRepository userRepository;

    public UserModel create(UserModel user){
        try{
            UserModel newUser = userRepository.findByEmail(user.getEmail());

            if(newUser != null){
                return null;
            }

            newUser = user;
            userRepository.save(newUser);
            return newUser;

        }catch(Exception ex){
            System.out.println("Error ao criar o usuario" + ex.getMessage());
            return null;
        }
    }

    public UserModel findById(Long id){
        try{
            Optional<UserModel> user = userRepository.findById(id);

            if(user.isPresent() == false){
                return null;
            }

            return user.get();

        }catch(Exception ex){
            System.out.println("Error ao encontrar o usupario: " + ex.getMessage());
            return null;
        }
    }

    public List<UserModel> findAll(){
        try{
            
            List<UserModel> users = userRepository.findAll();
            return users;

        }catch(Exception ex){

            System.out.println("Error ao encontrar os usuários: " + ex.getMessage());
            return null;

        }
    }
}

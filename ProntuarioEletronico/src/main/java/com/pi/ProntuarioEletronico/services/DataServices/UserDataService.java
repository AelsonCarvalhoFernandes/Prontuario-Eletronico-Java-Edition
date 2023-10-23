package com.pi.ProntuarioEletronico.services.DataServices;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.ProntuarioEletronico.models.user.UserModel;
import com.pi.ProntuarioEletronico.repositories.UserRepository.IUserRepository;

@Service
public class UserDataService {

    @Autowired
    private IUserRepository userRepository;

    /*
     * Find users
     */

    public UserModel findById(Long id){
        try{

            Optional<UserModel> user = userRepository.findById(id);

            if(user.isPresent()){
                return user.get();
            }

            return null;

        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    public UserModel findByCpf(String data){
        try {
            UserModel user = userRepository.findByCpf(data);

            return user;

        } catch (Exception ex) {
            System.out.println("Error: "+ ex.getMessage());
            return null;
        }
    }

    public List<UserModel> listAll(){
        try {

            return userRepository.findAll();
            
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    /*
     * Create users
     */

    public UserModel create(UserModel model){
        try{

            UserModel userCpf = this.findByCpf(model.getCpf());
            UserModel userRg = userRepository.findByRg(model.getRg());

            if(userCpf != null || userRg != null){
                return null;
            }

            UserModel user = model;
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());

            user = userRepository.save(user);

            return user;

        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    /*
     * Update Users
     */
    public UserModel update(UserModel model){
        try {

            UserModel user = this.findById(model.getId());

            if(user == null){
                return null;
            }

            BeanUtils.copyProperties(model, user);

            user.setUpdatedAt(LocalDateTime.now());

            user = userRepository.save(user);

            return user;
            
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    public boolean delete(Long id){
        try {
            
            UserModel user = this.findById(id);

            if(user == null){
                return false;
            }

            userRepository.delete(user);
            return true;

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return false;
        }
    }
    
}

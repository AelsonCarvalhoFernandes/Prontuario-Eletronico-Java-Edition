package com.PI.ProntuarioEletronico.services.dbServices;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.PI.ProntuarioEletronico.resources.dtos.cadastro.CadastroPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    //************************************************************************************
    // Metodo Criação de usuário
    //************************************************************************************
    public UserModel create(CadastroPaciente user){
        try{
            UserModel newUser = userRepository.findByEmail(user.email());

            if(newUser != null){
                return null;
            }

            newUser = new UserModel();

            newUser.setFirstName(user.firstName());
            newUser.setLastName(user.lastName());
            newUser.setEmail(user.email());
            newUser.setPassword(new BCryptPasswordEncoder().encode(user.password()));
            newUser.setCns(user.cns());
            newUser.setRg(user.rg());
            newUser.setCpf(user.cpf());
            newUser.setDataNascimento(user.dataNascimento());
            newUser.setTelefone(user.telefone());
            newUser.setEndereco(user.endereco());
            newUser.setNumero(user.numero());
            newUser.setBairro(user.bairro());
            newUser.setEstado(user.estado());

            newUser.setDataCadastro(LocalDateTime.now());
            newUser.setDataAtualizacao(LocalDateTime.now());

            userRepository.save(newUser);
            return newUser;

        }catch(Exception ex){
            System.out.println("Error ao criar o usuario" + ex.getMessage());
            return null;
        }
    }

    //******************************************************************************************
    // Metodo de busca de usuário por id
    //******************************************************************************************
    public UserModel findById(Long id){
        try{
            Optional<UserModel> user = userRepository.findById(id);

            if(!user.isPresent()){
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
    //**********************************************************************************************
    // Atualiza os dados dos usuários
    //**********************************************************************************************
    public UserModel update(Long id, CadastroPaciente updated){
        try{
            Optional<UserModel> user = userRepository.findById(id);

            if(user.isEmpty()){
                return null;
            }

            UserModel update = user.get();

            update.setFirstName(updated.firstName());
            update.setLastName(updated.lastName());
            update.setEmail(updated.email());
            update.setPassword(new BCryptPasswordEncoder().encode(updated.password()));
            update.setCns(updated.cns());
            update.setRg(updated.rg());
            update.setCpf(updated.cpf());
            update.setDataNascimento(updated.dataNascimento());
            update.setTelefone(updated.telefone());
            update.setEndereco(updated.endereco());
            update.setNumero(updated.numero());
            update.setBairro(updated.bairro());
            update.setEstado(updated.estado());

            update.setDataAtualizacao(LocalDateTime.now());

            userRepository.save(update);
            return update;

        }catch(Exception ex){
            System.out.println("Houve um error ao atualizar o usuário: "+ex);
            return null;
        }
    }
    //************************************************************************************
    // Metodo para deletar o usuário
    //*************************************************************************************
    public boolean deleteById(Long id){
        try{
            UserModel user = findById(id);
            if(user == null){
                return false;
            }

            userRepository.delete(user);
            return true;
        }catch (Exception ex){
            System.out.println("Houve um erro ao deletar o usuário: "+ex);
            return false;
        }
    }
}

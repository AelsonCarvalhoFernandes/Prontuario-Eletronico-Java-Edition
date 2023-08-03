package com.PI.ProntuarioEletronico.controllers;

import com.PI.ProntuarioEletronico.models.UserModel;
import com.PI.ProntuarioEletronico.repositories.UserRepository;
import com.PI.ProntuarioEletronico.resources.dtos.cadastro.CadastroPaciente;
import com.PI.ProntuarioEletronico.resources.enums.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("paciente")
public class PacienteController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable(value = "id") Long id){
        try{
            Optional<UserModel> user = userRepository.findById(id);

            if(user != null){
                return ResponseEntity.ok().body(user);
            }else{
                return ResponseEntity.ok("Não existe nenhum usuário com esse ID");
            }
        }catch (Exception ex){
            System.out.printf("Erro ao buscar usuário: " + ex);
            return ResponseEntity.ok("Ops!, Houve um erro ao buscar o usuário");
        }
    }

    @GetMapping("all")
    public ResponseEntity getAll(){
        List<UserModel> pacientes = userRepository.findByRoles(Roles.usuario);
        return ResponseEntity.ok().body(pacientes);
    }

    @PostMapping("cadastro")
    public ResponseEntity insertOne(@RequestBody CadastroPaciente cadastro){

        UserModel newUser = new UserModel();

        newUser.setFirstName(cadastro.firstName());
        newUser.setLastName(cadastro.lastName());
        newUser.setEmail(cadastro.email());
        newUser.setPassword(cadastro.password());
        newUser.setTelefone(cadastro.telefone());
        newUser.setEndereco(new BCryptPasswordEncoder().encode(cadastro.endereco()));
        newUser.setNumero(cadastro.numero());
        newUser.setBairro(cadastro.bairro());
        newUser.setCidade(cadastro.cidade());
        newUser.setCns(cadastro.cns());
        newUser.setCpf(cadastro.cpf());
        newUser.setRg(cadastro.rg());

        try{

            newUser.setRoles(Roles.usuario);
            newUser.setDataCadastro(new Date());
            newUser.setDataAtualizacao(new Date());



            userRepository.save(newUser);

            return ResponseEntity.ok().build();
        }catch (Exception ex){

            System.out.printf("Erro ao cadastrar o usuário: "+ ex);

            return ResponseEntity.badRequest().body(ex);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity updateOne(@PathVariable(value = "id") Long id,@RequestBody CadastroPaciente update){

        try{
            Optional<UserModel> user = userRepository.findById(id);

            UserModel updateUser = user.get();

            updateUser.setFirstName(update.firstName());
            updateUser.setLastName(update.lastName());
            updateUser.setEmail(update.email());
            updateUser.setPassword(new BCryptPasswordEncoder().encode(update.password()));
            updateUser.setCpf(update.cpf());
            updateUser.setCns(update.cns());
            updateUser.setRg(update.rg());
            updateUser.setEndereco(update.endereco());
            updateUser.setNumero(update.numero());
            updateUser.setCidade(update.cidade());
            updateUser.setBairro(update.bairro());
            updateUser.setTelefone(update.telefone());

            updateUser.setDataAtualizacao(new Date());

            userRepository.save(updateUser);

            return ResponseEntity.ok(HttpStatus.OK);

        }catch(Exception ex){
            System.out.printf("Ouve um erro ao atualizar: " + ex);
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteOne(@PathVariable(value = "id") Long id){
        userRepository.deleteById(id);

        return ResponseEntity.ok(HttpStatus.OK);
    }

}

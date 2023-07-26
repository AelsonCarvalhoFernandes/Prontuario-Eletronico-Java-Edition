package com.PI.ProntuarioEletronico.controllers;

import com.PI.ProntuarioEletronico.models.UserModel;
import com.PI.ProntuarioEletronico.repositories.UserRepository;
import com.PI.ProntuarioEletronico.resources.dtos.CadastroPaciente;
import com.PI.ProntuarioEletronico.resources.enums.Roles;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("paciente")
public class PacienteController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("cadastro")
    public ResponseEntity cadastro(@RequestBody CadastroPaciente cadastro){

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

    @GetMapping("all")
    public ResponseEntity pegarTodos(){
        List<UserModel> pacientes = userRepository.findByRoles(0);
        return ResponseEntity.ok().body(pacientes);
    }
}

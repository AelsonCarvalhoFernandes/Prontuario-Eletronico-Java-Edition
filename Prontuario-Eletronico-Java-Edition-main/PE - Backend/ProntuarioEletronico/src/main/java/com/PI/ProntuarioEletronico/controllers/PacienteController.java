package com.PI.ProntuarioEletronico.controllers;

import com.PI.ProntuarioEletronico.models.UserModel;
import com.PI.ProntuarioEletronico.resources.dtos.cadastro.CadastroPaciente;
import com.PI.ProntuarioEletronico.services.dbServices.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("paciente")
public class PacienteController {

    @Autowired
    private UserDbService userDbService;

    @GetMapping("all")
    public ResponseEntity getAll(){
        List<UserModel> pacientes = userDbService.findAll();
        if(pacientes == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(pacientes);

    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable(value = "id") Long id){
        UserModel paciente = userDbService.findById(id);

        if(paciente == null){
            return ResponseEntity.status(404).body("Não possui nenhum paciente com esse id cadastrado");
        }

        return ResponseEntity.ok().body(paciente);
    }

    @PostMapping("add")
    public ResponseEntity add(@RequestBody CadastroPaciente paciente){
        UserModel newPaciente = userDbService.create(paciente);

        if(paciente == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(newPaciente);
    }

    @PutMapping("update/{id}")
    public ResponseEntity update(@PathVariable(value = "id") Long id, CadastroPaciente updated){
        UserModel paciente = userDbService.update(id, updated);

        if(paciente == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(paciente);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id){
        boolean deleted = userDbService.deleteById(id);
        if(deleted == false){
            return ResponseEntity.badRequest().body("Houve um error ao deletar o usuário, verifique se o id está " +
                    "correto");
        }

        return ResponseEntity.ok().body("Usuário deletado com sucesso");
    }

}

package com.PI.ProntuarioEletronico.controllers;

import java.util.List;

import com.PI.ProntuarioEletronico.models.ReceituarioModel;
import com.PI.ProntuarioEletronico.resources.dtos.receitas.ReceituarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.PI.ProntuarioEletronico.models.ReceitaModel;
import com.PI.ProntuarioEletronico.resources.dtos.receitas.ReceitaDTO;
import com.PI.ProntuarioEletronico.services.dbServices.ReceitaDbService;

@RestController
@RequestMapping("receita")
public class ReceitaController {

    @Autowired
    private ReceitaDbService receitaDbService;

    @GetMapping("all")
    public ResponseEntity<List<ReceitaModel>> all(){
        List<ReceitaModel> receitas = receitaDbService.findAll();
        return ResponseEntity.status(HttpStatus.FOUND).body(receitas);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReceitaModel> getById(Long id){
        ReceitaModel receita = receitaDbService.findById(id);

        if(receita == null){
            return ResponseEntity.status((HttpStatus.NOT_FOUND)).build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(receita);
    }

    @PostMapping("create")
    public ResponseEntity create(ReceitaDTO newReceita){
        ReceitaModel receita = receitaDbService.create(newReceita);

        if(receita == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error ao criar a receita");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(receita);
    }

    @PutMapping("update/{id}")
    public ResponseEntity update(@PathVariable(value = "id") Long id, ReceitaDTO updated){
        ReceitaModel receita = receitaDbService.update(id, updated);

        if(receita == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error ao atualizar a receita");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(receita);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id){
        boolean deleted = receitaDbService.delete(id);
        
        if(deleted == false){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error ao deletar a receita");
        }

        return ResponseEntity.status(HttpStatus.OK).body(deleted);

    }

    @PostMapping("receituario/{id}")
    public ResponseEntity setReceituario(@PathVariable(value = "id") Long id, ReceituarioDto recetuario){
        boolean receituario = receitaDbService.setReceituario(id, recetuario);

        if(receituario == false){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("houve um error ao adicionar um medicamento a esta receita");
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("receituario/{id}")
    public ResponseEntity removeReceituario(@PathVariable(value = "id") Long id){
        boolean receituario = receitaDbService.deleteReceituario(id);

        if(receituario == false){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("houve um error ao remover um medicamento a esta receita");
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

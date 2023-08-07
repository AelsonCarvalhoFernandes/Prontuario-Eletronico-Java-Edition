package com.PI.ProntuarioEletronico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok().body(receitas);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReceitaModel> getById(Long id){
        ReceitaModel receita = receitaDbService.findById(id);

        return ResponseEntity.ok().body(receita);
    }

    @PostMapping("create")
    public ResponseEntity create(ReceitaDTO newReceita){
        ReceitaModel receita = receitaDbService.create(newReceita);

        if(receita == null){
            return ResponseEntity.badRequest().body("Error ao criar a receita");
        }

        return ResponseEntity.ok().body(receita);
    }

    @PutMapping("update/{id}")
    public ResponseEntity update(@PathVariable(value = "id") Long id, ReceitaDTO updated){
        ReceitaModel receita = receitaDbService.update(id, updated);

        if(receita == null){
            return ResponseEntity.badRequest().body("Error ao atualizar a receita");
        }

        return ResponseEntity.ok().body(receita);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id){
        boolean deleted = receitaDbService.delete(id);
        
        if(deleted == false){
            return ResponseEntity.badRequest().body("Error ao deletar a receita");
        }

        return ResponseEntity.ok().body(deleted);

    }

}

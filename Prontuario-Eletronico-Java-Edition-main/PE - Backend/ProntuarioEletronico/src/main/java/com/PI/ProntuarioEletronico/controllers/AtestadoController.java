package com.PI.ProntuarioEletronico.controllers;

import java.util.List;

import com.PI.ProntuarioEletronico.resources.dtos.atestados.AtestadoDTO;
import com.PI.ProntuarioEletronico.services.dbServices.AtestadoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.PI.ProntuarioEletronico.models.AtestadoModel;

@RestController
@RequestMapping("atestado")
public class AtestadoController {

    @Autowired
    private AtestadoDbService atestadoDbService;

    @GetMapping("all")
    public ResponseEntity<List<AtestadoModel>> all() {
        List<AtestadoModel> atesdados = atestadoDbService.findAll();
        return ResponseEntity.status(HttpStatus.FOUND).body(atesdados);

    }

    @GetMapping("id")
    public ResponseEntity<AtestadoModel> getById(Long id) {
        AtestadoModel atestado = atestadoDbService.findById(id);

        if(atestado == null){
            return ResponseEntity.status((HttpStatus.NOT_FOUND)).build();
        } else{
            return ResponseEntity.status(HttpStatus.FOUND).body(atestado);
        }
    }

    @PostMapping("create")
    public ResponseEntity create(AtestadoDTO newAtestado){
        AtestadoModel atestado = atestadoDbService.create(newAtestado);

        if(atestado == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar o atestado");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(atestado);
    }

    @PutMapping("update/{id}")
    public ResponseEntity update(@PathVariable(value = "id") Long id, AtestadoDTO updated){
        AtestadoModel atestado = atestadoDbService.update(id, updated);

        if(atestado == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar o atestado.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(atestado);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id){
        boolean deleted = atestadoDbService.delete(id);

        if(!deleted){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar o atestado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(deleted);

    }
}
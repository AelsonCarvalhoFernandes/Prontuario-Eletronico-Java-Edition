package com.PI.ProntuarioEletronico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PI.ProntuarioEletronico.models.LaudoModel;
import com.PI.ProntuarioEletronico.resources.dtos.laudo.laudoDto;
import com.PI.ProntuarioEletronico.services.dbServices.LaudoDbService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("laudo")
public class LaudoController {
    
    @Autowired
    private LaudoDbService laudoDbService;

    @PostMapping("create")
    public ResponseEntity<LaudoModel> create(@RequestBody laudoDto laudo){


        LaudoModel newLaudo = laudoDbService.create(laudo);

        return ResponseEntity.status(HttpStatus.CREATED).body(newLaudo);
    }

    @GetMapping("{id}")
    public ResponseEntity<LaudoModel> findById(@PathVariable(value = "id") Long id){
        LaudoModel laudo = laudoDbService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(laudo);
    }

    @GetMapping
    public ResponseEntity<List<LaudoModel>> findAll(){
        List<LaudoModel> laudos = laudoDbService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(laudos);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<LaudoModel> update(@PathVariable(value = "id")Long id, @RequestBody laudoDto laudo){
        LaudoModel update = laudoDbService.update(id ,laudo);

        return ResponseEntity.status(HttpStatus.OK).body(update);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id){
        laudoDbService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

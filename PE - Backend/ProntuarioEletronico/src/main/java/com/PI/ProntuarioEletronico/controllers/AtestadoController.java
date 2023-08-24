package com.PI.ProntuarioEletronico.controllers;

import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PI.ProntuarioEletronico.models.AtestadoModel;
import com.PI.ProntuarioEletronico.repositories.AtestadoRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("atestado")
public class AtestadoController {

    @Autowired
    private AtestadoRepository repository;

    @GetMapping
    public List<AtestadoModel> all() {
        return repository.findAll();

    }

    @PostMapping
    public AtestadoModel create(@RequestBody AtestadoModel atestado) {
        return repository.save(atestado);
    }

    @PutMapping
    public AtestadoModel updade(@RequestBody AtestadoModel atestado) {
        if (atestado.getId() > 0) {
            return repository.save(atestado);
        }
        return null;
    }

    public String delete(@RequestBody AtestadoModel atestado) {
        if (atestado.getId() > 0) {
            repository.delete(atestado);
            return "Removido com Sucesso!";
        }
        return "Usuário não encontrado";

    }

}

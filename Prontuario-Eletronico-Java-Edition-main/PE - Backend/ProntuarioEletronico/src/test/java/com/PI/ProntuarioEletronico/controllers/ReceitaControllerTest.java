package com.PI.ProntuarioEletronico.controllers;


import com.PI.ProntuarioEletronico.models.ReceitaModel;
import com.PI.ProntuarioEletronico.models.ReceituarioModel;
import com.PI.ProntuarioEletronico.models.UserModel;
import com.PI.ProntuarioEletronico.resources.dtos.receitas.ReceitaDTO;
import com.PI.ProntuarioEletronico.resources.dtos.receitas.ReceituarioDto;
import com.PI.ProntuarioEletronico.services.dbServices.ReceitaDbService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.mockito.Mockito.*;

/**
 *  Classe de testes do ReceitaController
 * */
@ExtendWith(MockitoExtension.class)
public class ReceitaControllerTest {

    ReceitaModel receita;
    ReceituarioModel receituario;
    ReceituarioDto receituarioDto;
    ReceitaDTO receitaDTO;
    UserModel user;
    UserModel medico;

    @InjectMocks
    private ReceitaController receitaController;
    @Mock
    private ReceitaDbService receitaDbService;

    @BeforeEach
    public void setup(){

        receita = new ReceitaModel();
        receituario = new ReceituarioModel();
        receituarioDto = new ReceituarioDto("Dipirona", "1x ao dia");
        user = new UserModel();
        medico = new UserModel();

        receitaDTO = new ReceitaDTO("Um resoumo", 1L, 1L);

        receita.setResumo("Um resumo qualquer");
        receita.setMedico(medico);
        receita.setUsers(user);

        receituario.setReceita(receita);
        receituario.setMedicamento("Dipirona");
        receituario.setUso("2x ao dia");

        receita.setReceituario(Collections.singletonList(receituario));
    }

    /**
    *  Teste da listagem de todas as receitas
    * */
    @Test
    public void metodo_all_deve_listar_todas_as_receitas_do_banco(){
        when(receitaDbService.findAll()).thenReturn(Collections.singletonList(receita));
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.FOUND).body(Collections.singletonList(receita)), receitaController.all());

        verify(receitaDbService).findAll();
        verifyNoMoreInteractions(receitaDbService);
    }

    /**
     *  Teste dos metodos de busca de receitas por id
     * */
    @Test
    public void metodo_getById_deve_retornar_uma_receita(){
        when(receitaDbService.findById(1L)).thenReturn(receita);
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.FOUND).body(receita), receitaController.getById(1L));

        verify(receitaDbService).findById(1L);
        verifyNoMoreInteractions(receitaDbService);
    }

    @Test
    public void metodo_getByid_deve_retornar_um_notFound(){
        when(receitaDbService.findById(1L)).thenReturn(null);
        Assertions.assertEquals(ResponseEntity.status((HttpStatus.NOT_FOUND)).build(), receitaController.getById(1L));

        verify(receitaDbService).findById(1L);
        verifyNoMoreInteractions(receitaDbService);
    }

    /**
     *  Teste da criação de novas receitas
     * */

    @Test
    public void metodo_create_deve_retornar_um_Status_Created_com_o_receita_criado(){
        when(receitaDbService.create(receitaDTO)).thenReturn(receita);
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.CREATED).body(receita), receitaController.create(receitaDTO));

        verify(receitaDbService).create(receitaDTO);
        verifyNoMoreInteractions(receitaDbService);
    }

    @Test
    public void metodo_create_deve_retornar_um_badRequest_ao_tentar_criar_receita(){
        when(receitaDbService.create(null)).thenReturn(null);
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error ao criar a receita"), receitaController.create(null));

        verify(receitaDbService).create(null);
        verifyNoMoreInteractions(receitaDbService);
    }

    /**
     *  Testes relacionado a atualização de receitas
     * */

    @Test
    public void metodo_update_deve_retornar_uma_receita_atualizada(){
        when(receitaDbService.update(1L, receitaDTO)).thenReturn(receita);
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.CREATED).body(receita), receitaController.update(1L, receitaDTO));

        verify(receitaDbService).update(1L, receitaDTO);
        verifyNoMoreInteractions(receitaDbService);
    }

    @Test
    public void metodo_update_deve_retornar_um_badRequest(){
        when(receitaDbService.update(null, null)).thenReturn(null);
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error ao atualizar a receita"), receitaController.update(null, null));

        verify(receitaDbService).update(null, null);
        verifyNoMoreInteractions(receitaDbService);
    }

    /**
     *  Teste do metodo para deletar receitas
     * */
    @Test
    public void metodo_delete_deve_retornar_um_status_deleted(){
        when(receitaDbService.delete(1L)).thenReturn(true);
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).body(true), receitaController.delete(1L));

        verify(receitaDbService).delete(1L);
        verifyNoMoreInteractions(receitaDbService);
    }

    @Test
    public void metodo_delete_deve_retornar_um_badRequest(){
        when(receitaDbService.delete(null)).thenReturn(false);
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error ao deletar a receita"), receitaController.delete(null));

        verify(receitaDbService).delete(null);
        verifyNoMoreInteractions(receitaDbService);
    }

    /**
     *  Teste do metodo para adicionar medicamento a receita
     * */

    @Test
    public void metodo_setReceituario_deve_retornar_um_status_created(){
        when(receitaDbService.setReceituario(1L, receituarioDto)).thenReturn(true);
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.CREATED).build(), receitaController.setReceituario(1L, receituarioDto));

        verify(receitaDbService).setReceituario(1L, receituarioDto);
        verifyNoMoreInteractions(receitaDbService);
    }

    @Test
    public void metodo_setReceituario_deve_retornar_um_badRequest(){
        when(receitaDbService.setReceituario(null, null)).thenReturn(false);
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("houve um error ao adicionar um medicamento a esta receita"), receitaController.setReceituario(null, null));

        verify(receitaDbService).setReceituario(null, null);
        verifyNoMoreInteractions(receitaDbService);
    }
}

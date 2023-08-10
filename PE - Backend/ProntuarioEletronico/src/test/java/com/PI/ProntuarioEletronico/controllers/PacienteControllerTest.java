package com.PI.ProntuarioEletronico.controllers;

import com.PI.ProntuarioEletronico.models.UserModel;
import com.PI.ProntuarioEletronico.resources.dtos.cadastro.CadastroPaciente;
import com.PI.ProntuarioEletronico.services.dbServices.UserDbService;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PacienteControllerTest {

    private UserModel user;
    private CadastroPaciente cadastro;

    @InjectMocks
    PacienteController pacienteController;

    @Mock
    UserDbService userDbService;

    @BeforeEach
    public void setup(){
        user = new UserModel();
        cadastro = new CadastroPaciente(
                "Aelson",
                "admin",
                "aelson@email.com",
                "12345",
                "73988776655",
                "9988776655",
                "5566778899",
                "5544332211",
                "Rua logo ali",
                "12345",
                "centro",
                "Eunápolis",
                "Bahia",
                LocalDateTime.now()
                );

        user.setFirstName(cadastro.firstName());
        user.setLastName(cadastro.lastName());
        user.setEstado(cadastro.email());
        user.setPassword(cadastro.password());
        user.setTelefone(cadastro.telefone());
        user.setRg(cadastro.rg());
        user.setCns(cadastro.cns());
        user.setCpf(cadastro.cpf());
        user.setEndereco(cadastro.endereco());
        user.setNumero(cadastro.numero());
        user.setBairro(cadastro.bairro());
        user.setCidade(cadastro.cidade());
        user.setEstado(cadastro.estado());
        user.setDataNascimento(cadastro.dataNascimento());
    }


    // Testes de adição de novos usuarios
    @Test
    public void metodo_add_deve_Cadastrar_um_paciente_e_retornar_o_mesmo(){
        when(userDbService.create(cadastro)).thenReturn(user);
        Assertions.assertEquals(ResponseEntity.ok().body(user), pacienteController.add(cadastro), "cadastrou " +
                "paciente");

        verify(userDbService).create(cadastro);
        verifyNoMoreInteractions(userDbService);
    }

    @Test
    public void metodo_add_deve_retornar_um_badRequest(){
        when(userDbService.create(null)).thenReturn(null);
        Assertions.assertEquals(ResponseEntity.badRequest().build(), pacienteController.add(null));

        verify(userDbService).create(null);
        verifyNoMoreInteractions(userDbService);
    }

    // Teste de retorno de todos os usuarios

    @Test
    public void metodo_getAll_deve_retornar_uma_lista_de_pacientes(){
        when(userDbService.findAll()).thenReturn(Collections.singletonList(user));
        Assertions.assertEquals(ResponseEntity.ok().body(Collections.singletonList(user)), pacienteController.getAll());
    }

    @Test
    public void metodo_getAll_deve_retornar_um_bad_request(){
        when(userDbService.findAll()).thenReturn(null);
        Assertions.assertEquals(ResponseEntity.badRequest().build(), pacienteController.getAll());
    }

    // Testes de busca usuarios por ID
    @Test
    public void metodo_getById_deve_retornar_um_usuario(){
        when(userDbService.findById(1L)).thenReturn(user);
        Assertions.assertEquals(ResponseEntity.ok().body(user), pacienteController.getById(1L));
    }

    @Test
    public void metodo_getById_deve_retornar_um_usuario_nao_encontrado(){
        when(userDbService.findById(1L)).thenReturn(null);
        Assertions.assertEquals(ResponseEntity.status(404).body("Não possui nenhum paciente com esse id cadastrado"), pacienteController.getById(1L));
    }

    // Teste de atualização de usuarios
    @Test
    public void metodo_update_deve_retornar_usuario_atualizado(){
        when(userDbService.update(1L, cadastro)).thenReturn(user);
        Assertions.assertEquals(ResponseEntity.ok().body(user), pacienteController.update(1L, cadastro));
    }

    @Test
    public void metodo_update_deve_retornar_badRequest_quando_paciente_null(){
        when(userDbService.update(1L, null)).thenReturn(null);
        Assertions.assertEquals(ResponseEntity.badRequest().build(), pacienteController.update(1L, null));
    }

    @Test
    public void metodo_update_deve_retornar_badRequest_quando_id_nul(){
        when(userDbService.update(null, cadastro)).thenReturn(null);
        Assertions.assertEquals(ResponseEntity.badRequest().build(), pacienteController.update(null, cadastro));
    }

    @Test
    public void metodo_update_deve_retornar_badRequest_quando_ambos_atributos_null(){
        when(userDbService.update(null, null)).thenReturn(null);
        Assertions.assertEquals(ResponseEntity.badRequest().build(), pacienteController.update(null, null));
    }

    // Teste ao deletar usuario

    @Test
    public void metodo_delete_deve_retornar_um_true(){
        when(userDbService.deleteById(1L)).thenReturn(true);
        Assertions.assertEquals(ResponseEntity.ok().body("Usuário deletado com sucesso"), pacienteController.delete(1L));
    }

    @Test
    public void metodo_delete_deve_retornar_um_false(){
        when(userDbService.deleteById(null)).thenReturn(false);
        Assertions.assertEquals(ResponseEntity.badRequest().body("Houve um error ao deletar o usuário, verifique se o id está " +
                "correto"), pacienteController.delete(null));
    }
}

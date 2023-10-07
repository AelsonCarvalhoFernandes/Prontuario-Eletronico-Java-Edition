package com.PI.ProntuarioEletronico.services.dbServices;

import com.PI.ProntuarioEletronico.models.AtestadoModel;
import com.PI.ProntuarioEletronico.repositories.AtestadoRepository;
import com.PI.ProntuarioEletronico.resources.dtos.atestados.AtestadoDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AtestadoDbService {
    private UserDbService userDbService;
    private AtestadoRepository atestadoRepository;


    //************************************************************************************
    // Pegar todos os atestados
    //************************************************************************************

    public List<AtestadoModel> findAll() {
        try {
            return atestadoRepository.findAll();
        } catch (Exception ex) {
            System.out.println("Houve um error ao carregar os atestados: " + ex);
            return null;

        }
    }

    // ********************************************************************************
    // Procura um atestado pelo ID
    //*********************************************************************************
    public AtestadoModel findById(Long id){
        try{
            Optional<AtestadoModel> atestado = atestadoRepository.findById(id);
            if(atestado.isEmpty()){
                return null;
            }

            return atestado.get();

        }catch(Exception ex){
            System.out.println("Houve um erro ao procurar o atestado: " + ex);
            return null;
        }
    }

    //*************************************************************************************
    // Cria uma nova receita
    //*************************************************************************************
    public AtestadoModel create(AtestadoDTO atestado){
        try{
            if(atestado == null){
                return null;
            }

            AtestadoModel newAtestado = new AtestadoModel();

            newAtestado.setUsers(userDbService.findById(atestado.idPaciente()));
            newAtestado.setMedico(userDbService.findById(atestado.idMedico()));
            newAtestado.setDiagnostico(atestado.diagnostico());
            newAtestado.setDataCriacao(LocalDateTime.now());
            newAtestado.setDataAtualizacao(LocalDateTime.now());
            atestadoRepository.save(newAtestado);
            return newAtestado;

        }catch (Exception ex){
            System.out.println("Houve um erro ao criar o atestado: " + ex);
            return null;
        }
    }

    //**************************************************************************************
    // Atualizar atestado
    //**************************************************************************************
    public AtestadoModel update(Long atestadoId, AtestadoDTO updated){
        try{
            AtestadoModel atestado = findById(atestadoId);

            if(atestado == null){
                return null;
            }

            atestado.setDiagnostico(updated.diagnostico());
            atestado.setDataAtualizacao(LocalDateTime.now());

            return atestado;

        }catch(Exception ex){
            System.out.println("Houve um error ao tentar encontrar o atestado: "+ex);
            return null;
        }
    }

    //*************************************************************************************
    // Deletar Receita
    //*************************************************************************************
    public boolean delete(Long id){
        try{
            AtestadoModel atestado = findById(id);

            if(atestado == null){
                return false;
            }

            atestadoRepository.delete(atestado);
            return true;

        }catch(Exception ex){
            System.out.println("Houve um error ao deletar o atestado: "+ex);
            return false;
        }
    }
}
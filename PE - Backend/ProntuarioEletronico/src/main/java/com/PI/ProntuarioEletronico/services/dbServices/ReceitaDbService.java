package com.PI.ProntuarioEletronico.services.dbServices;

import com.PI.ProntuarioEletronico.models.ReceitaModel;
import com.PI.ProntuarioEletronico.models.ReceituarioModel;
import com.PI.ProntuarioEletronico.repositories.ReceitaRepository;
import com.PI.ProntuarioEletronico.repositories.ReceituarioRepository;
import com.PI.ProntuarioEletronico.resources.dtos.receitas.ReceitaDTO;
import com.PI.ProntuarioEletronico.resources.dtos.receitas.ReceituarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReceitaDbService {
    @Autowired
    private UserDbService userDbService;
    @Autowired
    private ReceitaRepository receitaRepository;
    @Autowired
    private ReceituarioRepository receituarioRepository;

    //*************************************************************************************
    // Cria uma nova receita
    //*************************************************************************************
    public ReceitaModel create(ReceitaDTO receita){
        try{
            if(receita == null){
                return null;
            }

            ReceitaModel newReceita = new ReceitaModel();

            newReceita.setUsers(userDbService.findById(receita.PacienteId()));
            newReceita.setMedico(userDbService.findById(receita.MedicoId()));
            newReceita.setResumo(receita.resumo());
            newReceita.setDataCriacao(LocalDateTime.now());
            newReceita.setDataAtualizacao(LocalDateTime.now());
            receitaRepository.save(newReceita);
            return newReceita;

        }catch (Exception ex){
            System.out.println("Houve um error ao criar a receita: "+ex);
            return null;
        }
    }

    // ********************************************************************************
    // Procura uma receita pelo ID
    //*********************************************************************************
    public ReceitaModel findById(Long id){
        try{
            Optional<ReceitaModel> receita = receitaRepository.findById(id);
            if(receita.isEmpty()){
                return null;
            }

            return receita.get();

        }catch(Exception ex){
            System.out.println("Houve um erro ao encontrar a receita:" + ex);
            return null;
        }
    }

    //************************************************************************************
    // Pegar todas as receitas
    //************************************************************************************ 
    public List<ReceitaModel> findAll(){
        try{

            return receitaRepository.findAll();

        }catch(Exception ex){
            System.out.println("Houve um error ao carregar as receitas");
            return null;
        }
    }

    //**************************************************************************************
    // Atualizar receita
    //**************************************************************************************
    public ReceitaModel update(Long receitaId, ReceitaDTO updated){
        try{
            ReceitaModel receita = findById(receitaId);

            if(receita == null){
                return null;
            }

            receita.setResumo(updated.resumo());
            receita.setDataAtualizacao(LocalDateTime.now());

            return receita;

        }catch(Exception ex){
            System.out.println("Houve um error ao tentar encontrar uma receita: "+ex);
            return null;
        }
    }

    //*************************************************************************************
    // Deletar Receita
    //*************************************************************************************

    public boolean delete(Long id){
        try{
            ReceitaModel receita = findById(id);
            List<ReceituarioModel> receituarios = findReceituarios(receita.getId());

            if(receita == null){
                return false;
            }
            for (ReceituarioModel receituario : receituarios) {
                receituarioRepository.delete(receituario);
            }

            receitaRepository.delete(receita);
            return true;

        }catch(Exception ex){
            System.out.println("Houve um error ao deletar a receita: "+ex);
            return false;
        }
    }

    //*********************************************************************************
    // Atribui medicamentos a receita
    //*********************************************************************************
    private boolean setReceituario(Long receitaId,List<ReceituarioDto> receituarios){
        try{
            for (ReceituarioDto receituario : receituarios) {

                ReceitaModel receita = findById(receitaId);

                if(receita == null){
                    return false;
                }

                ReceituarioModel newReceituario = new ReceituarioModel();

                newReceituario.setMedicamento(receituario.medicamento());
                newReceituario.setUso(receituario.uso());

                receituarioRepository.save(newReceituario);

                receita.setDataAtualizacao(LocalDateTime.now());
            }
            return true;

        }catch(Exception ex){
            System.out.println("Error ao salva o medicamento: "+ex);
            return false;
        }
    }

    //************************************************************************************
    // Achar Receituarios
    //************************************************************************************
    public List<ReceituarioModel> findReceituarios(Long receitaId){
        try{

            if(receitaId == null){
                return null;
            }

            List<ReceituarioModel> receituarios = receituarioRepository.findByReceitaId(receitaId);

            return  receituarios;

        }catch(Exception ex){
            System.out.println("Houve um error ao encontrar receituarios: "+ex);
            return null;
        }
    }

    //*****************************************************************************************
    //Remover receituario
    //*****************************************************************************************
    public boolean deleteReceituario(Long id){
        try{
            Optional<ReceituarioModel> receituario = receituarioRepository.findById(id);
            if(receituario.isEmpty()){
                return false;
            }

            receituarioRepository.delete(receituario.get());
            return true;

        }catch (Exception ex){
            System.out.println("Houve um erro ao deletar o receituario: "+ex);
            return false;
        }
    }
}

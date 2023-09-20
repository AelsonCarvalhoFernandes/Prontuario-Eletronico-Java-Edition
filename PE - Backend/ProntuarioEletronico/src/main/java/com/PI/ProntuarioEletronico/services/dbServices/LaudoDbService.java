package com.PI.ProntuarioEletronico.services.dbServices;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.PI.ProntuarioEletronico.models.LaudoModel;
import com.PI.ProntuarioEletronico.models.UserModel;
import com.PI.ProntuarioEletronico.repositories.LaudoFilesRepository;
import com.PI.ProntuarioEletronico.repositories.LaudoRepository;
import com.PI.ProntuarioEletronico.resources.dtos.laudo.laudoDto;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class LaudoDbService {

    @Autowired
    private UserDbService userDbService;
    
    @Autowired
    private LaudoRepository laudoRepository;

    @Autowired
    private LaudoFilesRepository laudoFilesRepository;

    /*
     *  Criar um novo laudo
     */

    public LaudoModel create(laudoDto laudo){

        try{
            LaudoModel newLaudo = new LaudoModel();

            if(laudo == null){
                return null;
            }

            UserModel medico = userDbService.findById(laudo.idMedico());
            UserModel paciente = userDbService.findById(laudo.idPaciente());

            newLaudo.setDescricao(laudo.descricao());
            newLaudo.setConteudo(laudo.conteudo());
            newLaudo.setMedico(medico);
            newLaudo.setUsers(paciente);

            newLaudo.setCreatedAt(LocalDateTime.now());
            newLaudo.setUpdatedAt(LocalDateTime.now());

            laudoRepository.save(newLaudo);

            System.out.print("Chegou aqui");

            return newLaudo;


        }catch(Exception ex){
            System.out.println("Error: " + ex);
            return null;
        }

    }

    /*
     *  Retorna um laudo pelo ID
     */
    public LaudoModel findById(Long id){
        try{
            Optional<LaudoModel> laudo = laudoRepository.findById(id);

            if(laudo.isEmpty()){
                return null;
            }

            return laudo.get();

        }catch(Exception ex){
            System.out.println("Error: " + ex);
            return null;
        }
    }

    public List<LaudoModel> findAll(){
        try{
            List<LaudoModel> laudos = laudoRepository.findAll();

            return laudos;
        }catch(Exception ex){
            System.out.println("Error: " + ex);
            return null;
        }
    }

    /*
     *  Atualiza um laudo existente
     */

    public LaudoModel update(Long id,laudoDto laudo){

        try{
            LaudoModel update = findById(id);

            UserModel medico = userDbService.findById(laudo.idMedico());
            UserModel paciente = userDbService.findById(laudo.idPaciente());

            update.setConteudo(laudo.conteudo());
            update.setDescricao(laudo.descricao());
            update.setMedico(medico);
            update.setUsers(paciente);

            update.setUpdatedAt(LocalDateTime.now());

            laudoRepository.save(update);

            return update;

        }catch(Exception ex){
            System.out.println("Error: " + ex);
            return null;
        }

        
    }


    /*
     *  Deleta um laudo pelo ID
     */
    public boolean delete(Long id){
        try{
            LaudoModel laudo = findById(id);

            if(laudo == null){
                return false;
            }

            laudoRepository.delete(laudo);

            return true;


        }catch(Exception ex){
            System.out.println("Error: " + ex);
            return false;
        }
    }




    /*
     *  
     */
}

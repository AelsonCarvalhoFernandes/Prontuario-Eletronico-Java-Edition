package com.PI.ProntuarioEletronico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PI.ProntuarioEletronico.models.LaudoFilesModel;

@Repository
public interface LaudoFilesRepository extends JpaRepository<LaudoFilesModel, Long>{
    
}

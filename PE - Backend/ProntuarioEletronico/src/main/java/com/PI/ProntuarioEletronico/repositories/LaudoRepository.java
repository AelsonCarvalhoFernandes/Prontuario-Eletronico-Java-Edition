package com.PI.ProntuarioEletronico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PI.ProntuarioEletronico.models.LaudoModel;

@Repository
public interface LaudoRepository extends JpaRepository<LaudoModel, Long>{
    
}

package com.PI.ProntuarioEletronico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.PI.ProntuarioEletronico.models.AtestadoModel;
import org.springframework.stereotype.Repository;

@Repository
public interface AtestadoRepository extends JpaRepository<AtestadoModel, Long>{

}

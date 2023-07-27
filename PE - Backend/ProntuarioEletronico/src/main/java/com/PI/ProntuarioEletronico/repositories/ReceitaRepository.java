package com.PI.ProntuarioEletronico.repositories;

import com.PI.ProntuarioEletronico.models.ReceitaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaRepository extends JpaRepository<ReceitaModel, Long> {
}

package com.PI.ProntuarioEletronico.repositories;

import com.PI.ProntuarioEletronico.models.ReceituarioModel;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Entity
public interface ReceituarioRepository extends JpaRepository<ReceituarioModel, Long> {

    List<ReceituarioModel> findByReceitaId(Long Id);
}

package com.pi.ProntuarioEletronico.repositories.laudoRepository;

import com.pi.ProntuarioEletronico.models.prontuario.LaudoModel;
import com.pi.ProntuarioEletronico.models.user.typeUsers.PacientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILaudoRepository extends JpaRepository<LaudoModel, Long> {

    public List<LaudoModel> findByPacient(PacientModel pacient);
}

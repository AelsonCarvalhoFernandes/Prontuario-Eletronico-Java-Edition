package com.pi.ProntuarioEletronico.repositories.laudoRepository;

import com.pi.ProntuarioEletronico.models.prontuario.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFileRepository extends JpaRepository<FileModel, Long> {
}

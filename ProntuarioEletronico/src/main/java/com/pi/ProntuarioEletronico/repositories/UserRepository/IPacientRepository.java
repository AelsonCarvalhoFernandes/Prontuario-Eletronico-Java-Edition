package com.pi.ProntuarioEletronico.repositories.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pi.ProntuarioEletronico.models.user.UserModel;
import com.pi.ProntuarioEletronico.models.user.typeUsers.PacientModel;

@Repository
public interface IPacientRepository extends JpaRepository<PacientModel, Long>{
    
    public PacientModel findByUser(UserModel user);
    public PacientModel findByCns(String cns);
}

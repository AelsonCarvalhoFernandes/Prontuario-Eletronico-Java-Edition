package com.pi.ProntuarioEletronico.repositories.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pi.ProntuarioEletronico.models.user.UserModel;
import com.pi.ProntuarioEletronico.models.user.typeUsers.CollaboratorModel;

@Repository
public interface ICollaboratorRepository extends JpaRepository<CollaboratorModel, Long>{
    
    public CollaboratorModel findByUser(UserModel user);
}

package com.PI.ProntuarioEletronico.repositories;

import com.PI.ProntuarioEletronico.models.UserModel;
import com.PI.ProntuarioEletronico.resources.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    List<UserModel> findByRoles(Roles role);

    UserModel findByEmail(String email);
    
}

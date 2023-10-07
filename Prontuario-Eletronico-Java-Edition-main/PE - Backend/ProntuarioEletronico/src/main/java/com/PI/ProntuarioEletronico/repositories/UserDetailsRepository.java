package com.PI.ProntuarioEletronico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.PI.ProntuarioEletronico.models.UserModel;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserModel, Long>{

    UserDetails findByEmail(String email);
    

}

package com.PI.ProntuarioEletronico.repositories;

import com.PI.ProntuarioEletronico.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    List<UserModel> findByRoles(int role);
}

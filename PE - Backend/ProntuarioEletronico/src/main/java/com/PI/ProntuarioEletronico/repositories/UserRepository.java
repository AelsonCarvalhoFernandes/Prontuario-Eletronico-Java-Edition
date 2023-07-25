package com.PI.ProntuarioEletronico.repositories;

import com.PI.ProntuarioEletronico.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
}

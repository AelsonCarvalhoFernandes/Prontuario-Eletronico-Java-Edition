package com.pi.ProntuarioEletronico.repositories.UserRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.pi.ProntuarioEletronico.models.user.UserModel;
import com.pi.ProntuarioEletronico.resources.enums.Role;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, Long> {

    public UserModel findByCpf(String cpf);

    public List<UserModel> findByFirstName(String firstName);

    public UserModel findByRg(String rg);

    public List<UserModel> findByRole(Role role);

    // UserDetails findByEmail(String Email);
    UserDetails findByEmail(String email);
}
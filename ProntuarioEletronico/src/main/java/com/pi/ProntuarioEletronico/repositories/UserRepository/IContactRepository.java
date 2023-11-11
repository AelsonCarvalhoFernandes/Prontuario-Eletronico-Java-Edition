package com.pi.ProntuarioEletronico.repositories.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pi.ProntuarioEletronico.models.user.UserModel;
import com.pi.ProntuarioEletronico.models.user.contactUsers.ContactModel;

@Repository
public interface IContactRepository extends JpaRepository<ContactModel, Long>{
    
    public ContactModel findByUser(UserModel user);
}

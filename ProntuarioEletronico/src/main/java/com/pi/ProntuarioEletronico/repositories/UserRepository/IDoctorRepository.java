package com.pi.ProntuarioEletronico.repositories.UserRepository;

import com.pi.ProntuarioEletronico.models.user.UserModel;
import com.pi.ProntuarioEletronico.models.user.typeUsers.DoctorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDoctorRepository extends JpaRepository<DoctorModel, Long> {

    public DoctorModel findByUser(UserModel user);
    public DoctorModel findByCrm(String crm);
}

package com.pi.ProntuarioEletronico.resources.dtos;

import java.time.LocalDate;

public record PacientUpdateDto(

                Long id,
                String firstName,
                String lastName,
                String email,
                String password,
                String rg,
                String cpf,
                String cns,
                LocalDate dateBirth,
                String tipoSanguineo,
                String doencasPrevias,
                String street,
                String number,
                String city,
                String neighborhood,
                String telephone,
                String cellPhone

) {

}

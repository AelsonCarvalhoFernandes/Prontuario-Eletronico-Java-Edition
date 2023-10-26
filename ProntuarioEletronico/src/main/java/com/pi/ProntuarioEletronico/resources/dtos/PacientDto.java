package com.pi.ProntuarioEletronico.resources.dtos;

import java.time.LocalDate;

public record PacientDto(

    String firstName,
    String lastName,
    String email,
    String password,
    String rg,
    String cpf,

    LocalDate dateBirth,
    String tipoSanguineo,
    String doencasPrevias,
    String cns

) {
    
}
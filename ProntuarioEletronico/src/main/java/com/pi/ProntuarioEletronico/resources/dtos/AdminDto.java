package com.pi.ProntuarioEletronico.resources.dtos;

public record AdminDto(
    String firstName,
    String lastName,
    String email,
    String password,
    String rg,
    String cpf
) {
    
}

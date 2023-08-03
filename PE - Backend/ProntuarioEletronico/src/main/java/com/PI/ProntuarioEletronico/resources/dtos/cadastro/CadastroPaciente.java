package com.PI.ProntuarioEletronico.resources.dtos.cadastro;

import java.util.Date;

public record CadastroPaciente(String firstName,
                               String lastName,
                               String email,
                               String password,
                               String telefone,
                               String cpf,
                               String rg,
                               String cns,
                               String endereco,
                               String numero,
                               String bairro,
                               String cidade,
                               Date dataNascimento) {
}

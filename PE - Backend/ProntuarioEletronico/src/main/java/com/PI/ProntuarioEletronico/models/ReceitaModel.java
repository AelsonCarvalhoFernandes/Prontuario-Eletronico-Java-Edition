package com.PI.ProntuarioEletronico.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "receitas")
public class ReceitaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 255)
    private String resumo;
    @Column(nullable = false, length = 2000)
    private String conteudo;
    @Column(nullable = false)
    private Date dataCriacao;
    @Column(nullable = false)
    private Date dataAtualizacao;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel users;
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private UserModel medico;

}

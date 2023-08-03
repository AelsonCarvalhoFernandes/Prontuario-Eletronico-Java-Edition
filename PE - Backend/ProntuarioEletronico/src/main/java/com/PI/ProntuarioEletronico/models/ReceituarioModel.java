package com.PI.ProntuarioEletronico.models;

import jakarta.persistence.*;

@Entity
@Table(name = "receituarios")
public class ReceituarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "medicamento")
    private String medicamento;
    @Column(name = "uso")
    private String uso;
    @ManyToOne
    @JoinColumn(name = "receita_id")
    private ReceitaModel receita;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public ReceitaModel getReceita() {
        return receita;
    }

    public void setReceita(ReceitaModel receita) {
        this.receita = receita;
    }
}

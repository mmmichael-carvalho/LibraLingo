package com.libras.backend.model.quiz;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "perguntas")
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer level;

    @Enumerated(EnumType.STRING)
    private TipoPergunta tipo;

    @Column(length = 1000)
    private String prompt;

    private Integer indiceCorreto;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "opcoes", joinColumns = @JoinColumn(name = "pergunta_id"))
    private List<Opcao> opcoes = new ArrayList<>();

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public TipoPergunta getTipo() {
        return tipo;
    }

    public void setTipo(TipoPergunta tipo) {
        this.tipo = tipo;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public Integer getIndiceCorreto() {
        return indiceCorreto;
    }

    public void setIndiceCorreto(Integer indiceCorreto) {
        this.indiceCorreto = indiceCorreto;
    }

    public List<Opcao> getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(List<Opcao> opcoes) {
        this.opcoes = opcoes;
    }
}
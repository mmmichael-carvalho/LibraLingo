package com.libras.backend.model.quiz;

import java.util.ArrayList;
import java.util.List;

public class Pergunta {

    private Long id;
    private Integer level;
    private TipoPergunta tipo;
    private String prompt;
    private Integer indiceCorreto;
    private List<Opcao> opcoes = new ArrayList<>();

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
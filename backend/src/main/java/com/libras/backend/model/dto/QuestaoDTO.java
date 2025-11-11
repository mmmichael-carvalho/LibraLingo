package com.libras.backend.model.dto;

import com.libras.backend.model.quiz.TipoPergunta;
import com.libras.backend.model.quiz.Opcao;
import java.util.List;

public class QuestaoDTO {
    private Long id;
    private TipoPergunta tipo;
    private List<String> prompt;
    private List<Opcao> opcoes;
    private Integer indiceCorreto;

    public QuestaoDTO(Long id, TipoPergunta tipo, List<String> prompt, List<Opcao> opcoes, Integer indiceCorreto) {
        this.id = id;
        this.tipo = tipo;
        this.prompt = prompt;
        this.opcoes = opcoes;
        this.indiceCorreto = indiceCorreto;
    }

    public Long getId() { return id; }
    public TipoPergunta getTipo() { return tipo; }
    public List<String> getPrompt() { return prompt; }
    public List<Opcao> getOpcoes() { return opcoes; }
    public Integer getIndiceCorreto() { return indiceCorreto; }
}
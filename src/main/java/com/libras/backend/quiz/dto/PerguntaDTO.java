package com.libras.backend.quiz.dto;

import java.util.List;

public class PerguntaDTO {
    private Long id;
    private String sinalUrl;
    private List<String> opcoes;
    private Integer indiceCorreto;

    public PerguntaDTO() { }

    public PerguntaDTO(Long id, String sinalUrl, List<String> opcoes, Integer indiceCorreto) {
        this.id = id;
        this.sinalUrl = sinalUrl;
        this.opcoes = opcoes;
        this.indiceCorreto = indiceCorreto;
    }

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSinalUrl() { return sinalUrl; }
    public void setSinalUrl(String sinalUrl) { this.sinalUrl = sinalUrl; }

    public List<String> getOpcoes() { return opcoes; }
    public void setOpcoes(List<String> opcoes) { this.opcoes = opcoes; }

    public Integer getIndiceCorreto() { return indiceCorreto; }
    public void setIndiceCorreto(Integer indiceCorreto) { this.indiceCorreto = indiceCorreto; }
}

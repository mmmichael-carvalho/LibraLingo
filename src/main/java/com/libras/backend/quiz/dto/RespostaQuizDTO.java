package com.libras.backend.quiz.dto;

public class RespostaQuizDTO {
    private Long perguntaId;
    private Integer opcaoEscolhida;

    public RespostaQuizDTO() { }

    public RespostaQuizDTO(Long perguntaId, Integer opcaoEscolhida) {
        this.perguntaId = perguntaId;
        this.opcaoEscolhida = opcaoEscolhida;
    }

    // Getters e setters
    public Long getPerguntaId() { return perguntaId; }
    public void setPerguntaId(Long perguntaId) { this.perguntaId = perguntaId; }

    public Integer getOpcaoEscolhida() { return opcaoEscolhida; }
    public void setOpcaoEscolhida(Integer opcaoEscolhida) { this.opcaoEscolhida = opcaoEscolhida; }
}

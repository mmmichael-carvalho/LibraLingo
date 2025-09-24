package com.libras.backend.quiz.dto;

public class RespostaQuizDTO {
    private Long perguntaId;
    private Integer opcaoEscolhida;

    // ✅ ADICIONAR CONSTRUTOR PADRÃO
    public RespostaQuizDTO() {}
    public RespostaQuizDTO(Long perguntaId, Integer opcaoEscolhida) {
        this.perguntaId = perguntaId;
        this.opcaoEscolhida = opcaoEscolhida;
    }

    public Long getPerguntaId() { return perguntaId; }
    public Integer getOpcaoEscolhida() { return opcaoEscolhida; }

    // ✅ ADICIONAR SETTERS TAMBÉM
    public void setPerguntaId(Long perguntaId) { this.perguntaId = perguntaId; }
    public void setOpcaoEscolhida(Integer opcaoEscolhida) { this.opcaoEscolhida = opcaoEscolhida; }
}

package com.libras.backend.model.dto;

public class ResultadoQuizDTO {
    private int pontuacao;
    private int totalPerguntas;
    private String mensagem;

    public ResultadoQuizDTO() {}

    public ResultadoQuizDTO(int pontuacao, int totalPerguntas, String mensagem) {
        this.pontuacao = pontuacao;
        this.totalPerguntas = totalPerguntas;
        this.mensagem = mensagem;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getTotalPerguntas() {  // ✅ ADICIONA ESTE GETTER
        return totalPerguntas;
    }

    public void setTotalPerguntas(int totalPerguntas) {  // ✅ ADICIONA ESTE SETTER
        this.totalPerguntas = totalPerguntas;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
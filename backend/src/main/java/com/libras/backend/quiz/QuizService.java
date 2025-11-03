package com.libras.backend.quiz;

import com.libras.backend.model.quiz.Pergunta;
import com.libras.backend.quiz.dto.RespostaQuizDTO;
import com.libras.backend.quiz.dto.ResultadoQuizDTO;
import com.libras.backend.service.PerguntaService;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class QuizService {
    private final PerguntaService perguntaService;

    public QuizService(PerguntaService perguntaService) {
        this.perguntaService = perguntaService;
    }

    public ResultadoQuizDTO calculaResultado(List<RespostaQuizDTO> respostas) {
        int acertos = 0;

        for (RespostaQuizDTO resposta : respostas) {
            Pergunta pergunta = perguntaService.buscarPorId(resposta.getPerguntaId());
            if (pergunta != null && resposta.getOpcaoEscolhida().equals(pergunta.getIndiceCorreto())) {
                acertos++;
            }
        }

        String mensagem = acertos >= 4
                ? String.format("Parabéns! Você acertou %d de %d perguntas!", acertos, respostas.size())
                : String.format("Você acertou %d de %d. Tente novamente!", acertos, respostas.size());

        // ✅ CORREÇÃO: Adiciona respostas.size() como segundo parâmetro
        return new ResultadoQuizDTO(acertos, respostas.size(), mensagem);
    }
}
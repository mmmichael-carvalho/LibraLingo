package com.libras.backend.quiz;

import com.libras.backend.model.quiz.Pergunta;
import com.libras.backend.quiz.dto.RespostaQuizDTO;
import com.libras.backend.quiz.dto.ResultadoQuizDTO;
import main.java.com.libras.backend.repository.PerguntaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    private final PerguntaRepository perguntaRepository;

    public QuizService(PerguntaRepository perguntaRepository) {
        this.perguntaRepository = perguntaRepository;
    }

    public ResultadoQuizDTO calculaResultado(List<RespostaQuizDTO> respostas) {
        int acertos = 0;

        for (RespostaQuizDTO resposta : respostas) {
            Pergunta pergunta = perguntaRepository.findById(resposta.getPerguntaId()).orElse(null);
            if (pergunta != null && resposta.getOpcaoEscolhida().equals(pergunta.getIndiceCorreto())) {
                acertos++;
            }
        }

        String mensagem = acertos >= 4
                ? String.format("Parabéns! Você acertou %d de %d perguntas!", acertos, respostas.size())
                : String.format("Você acertou %d de %d. Tente novamente!", acertos, respostas.size());

        return new ResultadoQuizDTO(acertos, mensagem);
    }
}
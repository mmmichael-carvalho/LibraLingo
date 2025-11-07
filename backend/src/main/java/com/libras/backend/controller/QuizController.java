package com.libras.backend.controller;

import com.libras.backend.model.quiz.Pergunta;
import com.libras.backend.quiz.dto.QuestaoDTO;
import com.libras.backend.quiz.dto.RespostaQuizDTO;
import com.libras.backend.quiz.dto.ResultadoQuizDTO;
import com.libras.backend.service.PerguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
@CrossOrigin(origins = {"http://localhost:4200", "https://libralingo-production-bc0a.up.railway.app"})
public class QuizController {

    @Autowired
    private PerguntaService perguntaService;

    @PostMapping("/respostas")
    public ResponseEntity<ResultadoQuizDTO> calcularResultado(@RequestBody List<RespostaQuizDTO> respostas) {
        int acertos = 0;

        for (RespostaQuizDTO resposta : respostas) {
            Pergunta pergunta = perguntaService.buscarPorId(resposta.getPerguntaId());

            if (pergunta != null && resposta.getOpcaoEscolhida() != null) {
                if (resposta.getOpcaoEscolhida().equals(pergunta.getIndiceCorreto())) {
                    acertos++;
                }
            }
        }

        String mensagem = acertos >= 4
                ? String.format("Parabéns! Você acertou %d de %d perguntas!", acertos, respostas.size())
                : String.format("Você acertou %d de %d. Tente novamente!", acertos, respostas.size());

        ResultadoQuizDTO resultado = new ResultadoQuizDTO();
        resultado.setPontuacao(acertos);
        resultado.setTotalPerguntas(respostas.size());
        resultado.setMensagem(mensagem);

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/levels/{level}/questions")
    public ResponseEntity<List<QuestaoDTO>> getQuestoesPorNivel(@PathVariable Integer level) {
        List<QuestaoDTO> questoes = perguntaService.listarPorNivel(level);

        if (questoes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(questoes);
    }
}
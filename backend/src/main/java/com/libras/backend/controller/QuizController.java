package com.libras.backend.controller;

import com.libras.backend.model.Quiz;
import com.libras.backend.model.quiz.Pergunta;
import com.libras.backend.quiz.dto.QuestaoDTO;
import com.libras.backend.quiz.dto.RespostaQuizDTO;
import com.libras.backend.quiz.dto.ResultadoQuizDTO;
import com.libras.backend.service.JsonDataService;
import com.libras.backend.service.PerguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
@CrossOrigin(origins = {"http://localhost:4200", "https://libralingo.onrender.com"})
public class QuizController {

    @Autowired
    private JsonDataService jsonDataService;

    @Autowired
    private PerguntaService perguntaService;

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return jsonDataService.getAllQuizzes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        Quiz quiz = jsonDataService.getQuizById(id);
        if (quiz != null) {
            return ResponseEntity.ok(quiz);
        }
        return ResponseEntity.notFound().build();
    }

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

        // ✅ GERA A MENSAGEM
        String mensagem = acertos >= 4
                ? String.format("Parabéns! Você acertou %d de %d perguntas!", acertos, respostas.size())
                : String.format("Você acertou %d de %d. Tente novamente!", acertos, respostas.size());

        ResultadoQuizDTO resultado = new ResultadoQuizDTO();
        resultado.setPontuacao(acertos);
        resultado.setTotalPerguntas(respostas.size());
        resultado.setMensagem(mensagem);  // ✅ ADICIONA A MENSAGEM

        return ResponseEntity.ok(resultado);
    }

    // ✅ NOVO ENDPOINT PARA PERGUNTAS POR NÍVEL
    @GetMapping("/levels/{level}/questions")
    public ResponseEntity<List<QuestaoDTO>> getQuestoesPorNivel(@PathVariable Integer level) {
        List<QuestaoDTO> questoes = perguntaService.listarPorNivel(level);

        if (questoes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(questoes);
    }
}
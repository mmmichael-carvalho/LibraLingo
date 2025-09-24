package com.libras.backend.quiz;

import com.libras.backend.quiz.dto.QuestaoDTO;
import com.libras.backend.quiz.dto.RespostaQuizDTO;
import com.libras.backend.quiz.dto.ResultadoQuizDTO;
import com.libras.backend.service.PerguntaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/quiz")
@CrossOrigin(origins = "*")
public class QuizController {

    private final PerguntaService perguntaService;
    private final QuizService quizService;

    public QuizController(PerguntaService perguntaService, QuizService quizService) {
        this.perguntaService = perguntaService;
        this.quizService = quizService;
    }

    @GetMapping("/levels/{level}/questions")
    public ResponseEntity<List<QuestaoDTO>> buscarQuestoes(@PathVariable Integer level) {
        try {
            List<QuestaoDTO> questoes = perguntaService.listarPorNivel(level);
            return ResponseEntity.ok(questoes);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/respostas")
    public ResponseEntity<ResultadoQuizDTO> calcularResultado(@RequestBody List<RespostaQuizDTO> respostas) {
        try {
            ResultadoQuizDTO resultado = quizService.calculaResultado(respostas);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
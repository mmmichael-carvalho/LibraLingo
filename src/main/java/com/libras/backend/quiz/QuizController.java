package com.libras.backend.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// IMPORTAÇÕES CORRETAS PARA OS DTOs:
import com.libras.backend.quiz.dto.PerguntaDTO;
import com.libras.backend.quiz.dto.RespostaQuizDTO;
import com.libras.backend.quiz.dto.ResultadoQuizDTO;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    /**
     * GET /quiz
     * Retorna a lista de PerguntaDTO (sinalUrl + opções) para o front-end.
     */
    @GetMapping
    public ResponseEntity<List<PerguntaDTO>> getPerguntas() {
        List<PerguntaDTO> todas = quizService.listarPerguntas();
        return ResponseEntity.ok(todas);
    }

    /**
     * POST /quiz/submit
     * Recebe um JSON array de RespostaQuizDTO e devolve o ResultadoQuizDTO.
     */
    @PostMapping("/submit")
    public ResponseEntity<ResultadoQuizDTO> submitRespostas(
            @RequestBody List<RespostaQuizDTO> respostas) {
        ResultadoQuizDTO resultado = quizService.calculaResultado(respostas);
        return ResponseEntity.ok(resultado);
    }
}

package com.libras.backend.quiz;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.libras.backend.quiz.dto.QuestaoDTO;
import com.libras.backend.quiz.dto.RespostaQuizDTO;
import com.libras.backend.quiz.dto.ResultadoQuizDTO;
import com.libras.backend.model.quiz.TipoPergunta;
import com.libras.backend.model.quiz.Opcao;
import com.libras.backend.service.PerguntaService;
//import main.java.com.libras.backend.controller.QuizController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.anyList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(QuizController.class)
class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private QuizService quizService;

    @MockBean
    private PerguntaService perguntaService;

    @Test
    @DisplayName("GET /api/quiz/levels/1/questions → retorna JSON com QuestaoDTO")
    void deveRetornarListaDeQuestoes() throws Exception {


//        QuestaoDTO dto = new QuestaoDTO(
//                1L,
//                TipoPergunta.IMAGEM_PARA_TEXTO,
//                List.of("/images/oi.png"),
//                List.of(opcao1, opcao2),
//                0
//        );

//        given(perguntaService.listarPorNivel(1)).willReturn(List.of(dto));

        mockMvc.perform(get("/api/quiz/levels/1/questions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].tipo").value("IMAGEM_PARA_TEXTO"))
                .andExpect(jsonPath("$[0].prompt[0]").value("/images/oi.png"))
                .andExpect(jsonPath("$[0].opcoes[0].texto").value("Oi"));
    }

    @Test
    @DisplayName("POST /api/quiz/respostas → calcula resultado e retorna DTO")
    void deveCalcularResultado() throws Exception {
        ResultadoQuizDTO resultado = new ResultadoQuizDTO(2, "Parabéns! Você acertou 2 de 2 perguntas!");
        given(quizService.calculaResultado(anyList())).willReturn(resultado);

        List<RespostaQuizDTO> respostas = List.of(
                new RespostaQuizDTO(1L, 0),
                new RespostaQuizDTO(2L, 1)
        );

        mockMvc.perform(post("/api/quiz/respostas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(respostas)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pontuacao").value(2))
                .andExpect(jsonPath("$.mensagem").value("Parabéns! Você acertou 2 de 2 perguntas!"));
    }
}
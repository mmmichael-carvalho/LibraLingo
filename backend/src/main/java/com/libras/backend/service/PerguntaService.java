package com.libras.backend.service;

import com.libras.backend.model.dto.QuestaoDTO;
import com.libras.backend.model.quiz.Pergunta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerguntaService {

    private final JsonDataService jsonDataService;

    public PerguntaService(JsonDataService jsonDataService) {
        this.jsonDataService = jsonDataService;
    }

    public List<QuestaoDTO> listarPorNivel(Integer level) {
        return jsonDataService.getPerguntasByLevel(level)
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public Pergunta buscarPorId(Long id) {
        return jsonDataService.getPerguntaById(id);
    }

    private QuestaoDTO converterParaDTO(Pergunta p) {
        List<String> promptList = p.getPrompt() != null
                ? List.of(p.getPrompt().split(",\\s*"))
                : List.of();

        return new QuestaoDTO(
                p.getId(),
                p.getTipo(),
                promptList,
                p.getOpcoes(),
                p.getIndiceCorreto()
        );
    }
}
package com.libras.backend.service;

import com.libras.backend.quiz.dto.QuestaoDTO;
import com.libras.backend.repository.quiz.PerguntaRepository;
import com.libras.backend.model.quiz.Pergunta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerguntaService {

    private final PerguntaRepository repo;

    public PerguntaService(PerguntaRepository repo) {
        this.repo = repo;
    }

    public List<QuestaoDTO> listarPorNivel(Integer level) {
        return repo.findByLevel(level)
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }
    private QuestaoDTO converterParaDTO(Pergunta p) {
        // Simplificar parsing do prompt
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

    public List<Pergunta> listarTodas() {
        return repo.findAll();
    }

    public Pergunta salvar(Pergunta pergunta) {
        return repo.save(pergunta);
    }
}
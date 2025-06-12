package com.libras.backend.controller.quiz;

import com.libras.backend.model.quiz.Pergunta;
import com.libras.backend.repository.quiz.PerguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/admin/perguntas")
public class PerguntaController {
    @Autowired
    private PerguntaRepository perguntaRepository;
Mapping
    public List<Pergunta> listarTodas() {
        return perguntaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pergunta> obterPorId(@PathVariable Long id) {
        Optional<Pergunta> p = perguntaRepository.findById(id);
        return p.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Pergunta> criar(@RequestBody Pergunta pergunta) {
        Pergunta salvo = perguntaRepository.save(pergunta);
        URI uri = URI.create("/admin/perguntas/" + salvo.getId());
        return ResponseEntity.created(uri).body(salvo);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Pergunta> atualizar(
            @PathVariable Long id,
            @RequestBody Pergunta dadosAtualizados) {
        return perguntaRepository.findById(id)
                .map(existente -> {
                    existente.setSinalUrl(dadosAtualizados.getSinalUrl());
                    existente.setIndiceCorreto(dadosAtualizados.getIndiceCorreto());
                    existente.setOpcoes(dadosAtualizados.getOpcoes());
                    Pergunta salvo = perguntaRepository.save(existente);
                    return ResponseEntity.ok(salvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!perguntaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        perguntaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
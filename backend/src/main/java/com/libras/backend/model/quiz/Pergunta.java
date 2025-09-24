package com.libras.backend.model.quiz;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Pergunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer level;

    @Enumerated(EnumType.STRING)
    private TipoPergunta tipo;

    private String prompt;
    private Integer indiceCorreto;

    @OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Opcao> opcoes = new ArrayList<>();

    // Getters e Setters básicos
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getLevel() { return level; }
    public void setLevel(Integer level) { this.level = level; }

    public TipoPergunta getTipo() { return tipo; }
    public void setTipo(TipoPergunta tipo) { this.tipo = tipo; }

    public String getPrompt() { return prompt; }
    public void setPrompt(String prompt) { this.prompt = prompt; }

    public Integer getIndiceCorreto() { return indiceCorreto; }
    public void setIndiceCorreto(Integer indiceCorreto) { this.indiceCorreto = indiceCorreto; }

    public List<Opcao> getOpcoes() { return opcoes; }

    public void setOpcoes(List<Opcao> opcoes) {
        this.opcoes.clear();
        if (opcoes != null) {
            opcoes.forEach(o -> o.setPergunta(this));
            this.opcoes.addAll(opcoes);
        }
    }
}

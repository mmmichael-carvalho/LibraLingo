package com.libras.backend.model.quiz;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pergunta")
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String sinalUrl;

    // Cada Pergunta terá várias Opções relacionadas
    @OneToMany(mappedBy = "pergunta",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Opcao> opcoes = new ArrayList<>();

    @Column(nullable = false)
    private Integer indiceCorreto;

    public Pergunta() {
    }

    public Pergunta(String sinalUrl, List<Opcao> opcoes, Integer indiceCorreto) {
        this.sinalUrl = sinalUrl;
        this.opcoes = opcoes;
        this.indiceCorreto = indiceCorreto;
        if (opcoes != null) {
            for (Opcao o : opcoes) {
                o.setPergunta(this);
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSinalUrl() {
        return sinalUrl;
    }

    public void setSinalUrl(String sinalUrl) {
        this.sinalUrl = sinalUrl;
    }

    public List<Opcao> getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(List<Opcao> opcoes) {
        this.opcoes = opcoes;
        if (opcoes != null) {
            for (Opcao o : opcoes) {
                o.setPergunta(this);
            }
        }
    }

    public Integer getIndiceCorreto() {
        return indiceCorreto;
    }

    public void setIndiceCorreto(Integer indiceCorreto) {
        this.indiceCorreto = indiceCorreto;
    }
}

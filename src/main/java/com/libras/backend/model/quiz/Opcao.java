package com.libras.backend.model.quiz;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "OPCAO")
public class Opcao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TEXTO", nullable = false)
    private String texto;

    /**
     * Toda opção pertence a uma Pergunta.
     * @JsonIgnore evita recursão no JSON.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PERGUNTA_ID")
    @JsonIgnore
    private Pergunta pergunta;

    public Opcao() { }

    public Opcao(String texto) {
        this.texto = texto;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Pergunta getPergunta() {
        return pergunta;
    }
    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }
}

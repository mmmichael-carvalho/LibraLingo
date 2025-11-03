package com.libras.backend.model.quiz;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Opcao {

    @Column(length = 500)
    private String texto;

    @Column(length = 500)
    private String imagemUrl;

    // Getters e Setters
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }
}
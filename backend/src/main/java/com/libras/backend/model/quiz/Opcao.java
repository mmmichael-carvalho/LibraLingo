package com.libras.backend.model.quiz;

import jakarta.persistence.*;

@Entity
public class Opcao {

    public class Opcao {
        private Long id;
        private String texto;
        private String imagemUrl;


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

        public String getImagemUrl() {
            return imagemUrl;
        }

        public void setImagemUrl(String imagemUrl) {
            this.imagemUrl = imagemUrl;
        }

        public Pergunta getPergunta() {
            return pergunta;
        }

        public void setPergunta(Pergunta pergunta) {
            this.pergunta = pergunta;
        }
    }

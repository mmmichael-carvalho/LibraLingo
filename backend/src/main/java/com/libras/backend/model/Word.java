package com.libras.backend.model;

public class Word {
    private Long id;
    private String word;
    private String description;
    private String imageUrl;
    private String category;

    // Construtores
    public Word() {}

    public Word(Long id, String word, String description, String imageUrl, String category) {
        this.id = id;
        this.word = word;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

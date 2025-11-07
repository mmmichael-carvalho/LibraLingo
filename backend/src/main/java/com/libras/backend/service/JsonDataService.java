package com.libras.backend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.libras.backend.model.Quiz;
import com.libras.backend.model.Word;
import com.libras.backend.model.quiz.Pergunta;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JsonDataService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<Word> words = new ArrayList<>();
    private List<Quiz> quizzes = new ArrayList<>();
    private List<Pergunta> perguntas = new ArrayList<>();  // NOVO!

    @PostConstruct
    public void loadData() {
        try {

            InputStream wordsStream = new ClassPathResource("data/words.json").getInputStream();
            words = objectMapper.readValue(wordsStream, new TypeReference<List<Word>>() {});

            InputStream quizStream = new ClassPathResource("data/quiz.json").getInputStream();
            quizzes = objectMapper.readValue(quizStream, new TypeReference<List<Quiz>>() {});

            InputStream perguntasStream = new ClassPathResource("data/perguntas.json").getInputStream();
            perguntas = objectMapper.readValue(perguntasStream, new TypeReference<List<Pergunta>>() {});

            System.out.println("Dados carregados com sucesso!");
            System.out.println(words.size() + " palavras carregadas");
            System.out.println(quizzes.size() + " quizzes carregados");
            System.out.println(perguntas.size() + " perguntas carregadas");  // NOVO!

        } catch (IOException e) {
            System.err.println("Erro ao carregar dados JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public List<Word> getAllWords() {
        return words;
    }

    public Word getWordById(Long id) {
        return words.stream()
                .filter(word -> word.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Quiz> getAllQuizzes() {
        return quizzes;
    }

    public Quiz getQuizById(Long id) {
        return quizzes.stream()
                .filter(quiz -> quiz.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Pergunta> getAllPerguntas() {
        return perguntas;
    }

    public Pergunta getPerguntaById(Long id) {
        return perguntas.stream()
                .filter(pergunta -> pergunta.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Pergunta> getPerguntasByLevel(Integer level) {
        return perguntas.stream()
                .filter(pergunta -> pergunta.getLevel().equals(level))
                .collect(Collectors.toList());
    }
}
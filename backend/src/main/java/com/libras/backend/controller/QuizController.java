package com.libras.backend.controller;

import com.libras.backend.model.Quiz;
import com.libras.backend.service.JsonDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
@CrossOrigin(origins = {"http://localhost:4200", "https://libralingo.onrender.com"})
public class QuizController {

    @Autowired
    private JsonDataService jsonDataService;

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return jsonDataService.getAllQuizzes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        Quiz quiz = jsonDataService.getQuizById(id);
        if (quiz != null) {
            return ResponseEntity.ok(quiz);
        }
        return ResponseEntity.notFound().build();
    }
}

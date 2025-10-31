package com.libras.backend.controller;

import com.libras.backend.model.Word;
import com.libras.backend.service.JsonDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/words")
@CrossOrigin(origins = {"http://localhost:4200", "https://libralingo.onrender.com"})
public class WordController {

    @Autowired
    private JsonDataService jsonDataService;

    @GetMapping
    public List<Word> getAllWords() {
        return jsonDataService.getAllWords();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Word> getWordById(@PathVariable Long id) {
        Word word = jsonDataService.getWordById(id);
        if (word != null) {
            return ResponseEntity.ok(word);
        }
        return ResponseEntity.notFound().build();
    }
}

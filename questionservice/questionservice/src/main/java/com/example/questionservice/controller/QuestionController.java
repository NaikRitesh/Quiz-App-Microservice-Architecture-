package com.example.questionservice.controller;

import com.example.questionservice.model.Question;
import com.example.questionservice.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    // Fetch Random Questions
    @GetMapping("/random/{count}")
    public List<Question> getRandomQuestions(@PathVariable int count) {
        if (count <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Count must be greater than 0");
        }
        return questionRepository.getRandomQuestions(count);
    }

    // Add a new question
    @PostMapping("/add")
    public String addQuestion(@RequestBody Question question) {
        questionRepository.save(question);
        return "Question added successfully!";
    }

    // Fetch questions by IDs
    @PostMapping("/by-ids")
    public List<Question> getQuestionsByIds(@RequestBody List<Long> ids) {
        return questionRepository.findByIdIn(ids);
    }
}
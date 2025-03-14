package com.example.quizservice.controller;

import com.example.quizservice.client.QuestionClient;
import com.example.quizservice.dto.QuestionDTO;
import com.example.quizservice.model.Quiz;
import com.example.quizservice.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuestionClient questionClient;

    @Autowired
    private QuizRepository quizRepository;

    // Start a New Quiz
    @PostMapping("/start")
    public Quiz startQuiz() {
        List<QuestionDTO> questions = questionClient.getRandomQuestions(10);

        Quiz quiz = new Quiz();
        quiz.setQuestionIds(questions.stream().map(QuestionDTO::getId).collect(Collectors.toList()));
        quiz.setScore(0);
        quizRepository.save(quiz);
        return quiz;
    }

    // Submit Quiz and Get Score
    @PostMapping("/submit/{quizId}")
    public String submitQuiz(@PathVariable Long quizId, @RequestBody Map<Long, String> userAnswers) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found!"));

        List<QuestionDTO> questions = questionClient.getQuestionsByIds(quiz.getQuestionIds()); // Fetch questions by IDs
        int score = 0;

        for (QuestionDTO question : questions) {
            String correctAnswer = question.getCorrectAnswer();
            String userAnswer = userAnswers.get(question.getId());

            if (userAnswer != null && userAnswer.equalsIgnoreCase(correctAnswer)) {
                score++;
            }
        }

        quiz.setScore(score);
        quizRepository.save(quiz);
        return "Your score: " + score + " / 10";
    }
}
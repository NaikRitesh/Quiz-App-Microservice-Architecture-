package com.example.questionservice.repository;

import com.example.questionservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    default List<Question> getRandomQuestions(int count) {
        List<Question> allQuestions = findAll(); // Fetch all questions
        Collections.shuffle(allQuestions); // Shuffle the list
        return allQuestions.stream().limit(count).toList(); // Pick 'count' random questions
    }

    List<Question> findByIdIn(List<Long> ids); // Fetch questions by IDs
}
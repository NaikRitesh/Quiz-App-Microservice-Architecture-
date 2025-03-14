package com.example.quizservice.client;

import com.example.quizservice.dto.QuestionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "questionservice", path = "/api/questions")
public interface QuestionClient {

    @GetMapping("/random/{count}")
    List<QuestionDTO> getRandomQuestions(@PathVariable int count);

    @PostMapping("/by-ids")
    List<QuestionDTO> getQuestionsByIds(@RequestBody List<Long> ids);
}
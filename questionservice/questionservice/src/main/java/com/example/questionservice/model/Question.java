package com.example.questionservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "questions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String category;

    @NotBlank
    private String difficulty;

    @NotBlank
    private String questionText;

    @NotBlank
    private String option1;

    @NotBlank
    private String option2;

    @NotBlank
    private String option3;

    @NotBlank
    private String option4;

    @NotBlank
    private String correctAnswer; // "option1", "option2", etc.
}
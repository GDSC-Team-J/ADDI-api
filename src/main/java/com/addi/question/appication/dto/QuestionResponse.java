package com.addi.question.appication.dto;

import com.addi.question.domain.Questions;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class QuestionResponse {
    List<Questions> question;
}

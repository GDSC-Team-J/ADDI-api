package com.addi.question.appication.dto;

import com.addi.question.domain.Questions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class QuestionResponse {
    List<String> questions;
}

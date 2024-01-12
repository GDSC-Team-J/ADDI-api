package com.addi.question.appication;

import com.addi.global.exception.BusinessException;
import com.addi.question.appication.dto.QuestionResponse;
import com.addi.question.domain.Questions;
import com.addi.stt.exception.VoiceError;
import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService {

	public QuestionResponse getQuestionService(){
		List<String> randomQuestions = Questions.getRandomQuestions(5);
		QuestionResponse question  = new QuestionResponse(randomQuestions);
		return question;
	}
}

package com.addi.translation.application;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PapagoService {

	public String translateEngToKor(List<String> sentences) {
		String apiUrl = "https://openapi.naver.com/v1/papago/n2mt";
		String clientId = "YOUR_CLIENT_ID";
		String clientSecret = "YOUR_CLIENT_SECRET";
		String sourceLanguage = "ko";
		String targetLanguage = "en";
		String textToTranslate = String.join(" ", sentences);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.set("X-Naver-Client-Id", clientId);
		headers.set("X-Naver-Client-Secret", clientSecret);

		String requestBody = "source=" + sourceLanguage +
			"&target=" + targetLanguage +
			"&text=" + textToTranslate;

		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity,
			String.class);

		String translatedMessage = responseEntity.getBody();
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			String translatedText = translatedMessage;
			System.out.println("Translated Text: " + translatedText);
		} else {
			System.err.println("Error: " + responseEntity.getStatusCode());
		}

		System.out.println("translatedMessage = " + translatedMessage);
		return "s";
	}
}

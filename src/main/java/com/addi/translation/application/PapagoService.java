package com.addi.translation.application;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PapagoService {

	public String translateEngToKor(List<String> sentences) {
		String apiUrl = "https://openapi.naver.com/v1/papago/n2mt";
		String clientId = "XwkYis3o996stJfADs0s";
		String clientSecret = "VDAAsDy3pq";
		String sourceLanguage = "ko";  // Change to "en" for English
		String targetLanguage = "en";  // Change to "ko" for Korean

		// Join sentences with space
		String textToTranslate = String.join(" ", sentences);

		try {
			// URL-encode the text
			textToTranslate = URLEncoder.encode(textToTranslate, StandardCharsets.UTF_8);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.set("X-Naver-Client-Id", clientId);
			headers.set("X-Naver-Client-Secret", clientSecret);

			// Build the request body
			String requestBody = String.format("source=%s&target=%s&text=%s", sourceLanguage, targetLanguage,
				textToTranslate);

			HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity,
				String.class);

			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				String jsonLog = responseEntity.getBody();
				System.out.println("Translated Text: " + jsonLog);

				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode jsonNode = objectMapper.readTree(jsonLog);

				// "message" 노드에 해당하는 JsonNode 추출
				JsonNode messageNode = jsonNode.path("message");

				// "result" 노드에 해당하는 JsonNode 추출
				JsonNode resultNode = messageNode.path("result");

				// "translatedText" 노드에 해당하는 문자열 추출
				return resultNode.path("translatedText").asText();

			} else {
				System.err.println("Error: " + responseEntity.getStatusCode());
				return null;  // Or handle the error accordingly
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;  // Or handle the exception accordingly
		}
	}
}

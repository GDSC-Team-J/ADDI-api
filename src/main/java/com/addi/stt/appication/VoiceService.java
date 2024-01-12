package com.addi.stt.appication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.addi.global.exception.BusinessException;
import com.addi.stt.appication.dto.VoiceResponse;
import com.addi.stt.exception.VoiceError;
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.protobuf.ByteString;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class VoiceService {

	public VoiceResponse convert(String macAddress, List<MultipartFile> files) {
		if (files.isEmpty()) {
			BusinessException.of(VoiceError.EMPTY_VOICE);
		}

		List<String> transcripts = new ArrayList<>();
		for (MultipartFile file : files) {
			try (SpeechClient speechClient = SpeechClient.create()) {
				// 오디오 파일을 byte array로 decode
				byte[] audioBytes = file.getBytes();

				// 오디오 객체 생성
				ByteString audioData = ByteString.copyFrom(audioBytes);
				RecognitionAudio recognitionAudio = RecognitionAudio.newBuilder()
					.setContent(audioData)
					.build();

				// 설정 객체 생성
				RecognitionConfig recognitionConfig = RecognitionConfig.newBuilder()
					.setEncoding(RecognitionConfig.AudioEncoding.ENCODING_UNSPECIFIED)
					.setSampleRateHertz(44100)
					.setLanguageCode("ko-KR") // 한국어로 설정
					.build();

				// 오디오-텍스트 변환 수행
				RecognizeResponse response = speechClient.recognize(recognitionConfig, recognitionAudio);
				List<SpeechRecognitionResult> results = response.getResultsList();

				if (!results.isEmpty()) {
					// 주어진 말 뭉치에 대해 여러 가능한 스크립트를 제공. 0번(가장 가능성 있는)을 사용한다.
					SpeechRecognitionResult result = results.get(0);
					transcripts.add(result.getAlternatives(0).getTranscript());
				} else {
					transcripts.add(""); // 빈 문자열을 추가하거나 다른 처리를 수행할 수 있습니다.
				}
			} catch (Exception e) {
				throw new RuntimeException("Error processing audio file: " + file.getOriginalFilename(), e);
			}
		}
		return new VoiceResponse(transcripts);
	}

}

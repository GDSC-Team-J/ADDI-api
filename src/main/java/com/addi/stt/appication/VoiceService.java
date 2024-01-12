package com.addi.stt.appication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.addi.global.exception.BusinessException;
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

	public List<String> convert(
		String macAddress,
		List<MultipartFile> files
	) {
		if (files.isEmpty()) {
			BusinessException.of(VoiceError.EMPTY_FILE);
		}

		List<String> transcripts = new ArrayList<>();
		for (MultipartFile file : files) {
			//Google-Speech-to-Text 서비스 접근 클라이언트 생성
			try (SpeechClient speechClient = SpeechClient.create()) {

				// multipartFile에서 byte배열 데이터 추출
				byte[] audioBytes = file.getBytes();

				// 오디오 객체 생성 (byte 배열 -> byteString -> RecognitionAudio 객체)
				ByteString audioData = ByteString.copyFrom(audioBytes);
				RecognitionAudio recognitionAudio = RecognitionAudio.newBuilder()
					.setContent(audioData)
					.build();

				// 오디오 객체 설정 (오디오 객체의 인코딩 타입, 샘플 레이트, 언어 코드 설정)
				RecognitionConfig recognitionConfig = RecognitionConfig.newBuilder()
					.setEncoding(RecognitionConfig.AudioEncoding.ENCODING_UNSPECIFIED)
					.setSampleRateHertz(44100)
					.setLanguageCode("ko-KR") // 한국어로 설정
					.build();

				// 오디오-텍스트 변환 수행 (오디오 객체, 오디오 객체 설정 정보를 가지고 텍스트로 변환 수행)
				RecognizeResponse response = speechClient.recognize(recognitionConfig, recognitionAudio);
				List<SpeechRecognitionResult> results = response.getResultsList();

				if (!results.isEmpty()) {
					// 주어진 말 뭉치에 대해 여러 가능한 스크립트를 제공. 0번(가장 가능성 있는)을 사용
					SpeechRecognitionResult result = results.get(0);
					transcripts.add(result.getAlternatives(0).getTranscript());
				}

			} catch (Exception e) {
				BusinessException.of(VoiceError.NOT_TRANSLATOR); //SST 변환 실패
			}
		}

		//변환된 텍스트가 없을시 -> 녹음이 제대로 안됨.
		if(transcripts.isEmpty()){
			BusinessException.of(VoiceError.EMPTY_VOICE);
		}

		return transcripts;
	}
}

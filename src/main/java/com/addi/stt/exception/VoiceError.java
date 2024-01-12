package com.addi.stt.exception;

import com.addi.global.exception.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum VoiceError implements ErrorCode {
	EMPTY_FILE("파일이 없습니다.", NOT_FOUND, "V_001"),
	EMPTY_VOICE("녹음된 음성이 없습니다.", NOT_FOUND,"V_002"),
	NOT_TRANSLATOR("텍스트 변환 중 문제가 발생하였습니다.", NOT_FOUND,"V_003");

	private final String message;
	private final HttpStatus status;
	private final String code;
}

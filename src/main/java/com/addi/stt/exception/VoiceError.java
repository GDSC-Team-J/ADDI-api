package com.addi.stt.exception;

import com.addi.global.exception.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum VoiceError implements ErrorCode {
	EMPTY_VOICE("음성파일이 없습니다.", NOT_FOUND, "V_001");

	private final String message;
	private final HttpStatus status;
	private final String code;
}

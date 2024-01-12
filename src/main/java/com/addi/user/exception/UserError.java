package com.addi.user.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import com.addi.global.exception.error.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserError implements ErrorCode {
	NOT_SIGN_UP("회원 가입된 기기가 아닙니다.", NOT_FOUND, "U_001");

	private final String message;
	private final HttpStatus status;
	private final String code;
}

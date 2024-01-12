package com.addi.user.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import com.addi.global.exception.error.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserError implements ErrorCode {
	NOT_SIGN_UP("회원 가입된 기기가 아닙니다.", NOT_FOUND, "U_001"),
	ALREADY_SIGN_UP("이미 회원가입된 유저입니다.", BAD_REQUEST, "U_002"),
	INVITATION_CODE_NOT_FOUND("존재하지 않는 초대코드입니다.", NOT_FOUND, "U_003");

	private final String message;
	private final HttpStatus status;
	private final String code;
}

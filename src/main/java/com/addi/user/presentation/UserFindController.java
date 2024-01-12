package com.addi.user.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.addi.user.application.UserLoginService;
import com.addi.user.application.dto.UserLoginResponse;
import com.addi.user.application.dto.UserSignUpResponse;

import lombok.RequiredArgsConstructor;

@RestController
@Transactional
@RequiredArgsConstructor
public class UserFindController {

	private final UserLoginService userLoginService;

	@GetMapping("/api/login")
	public ResponseEntity<UserLoginResponse> login(@RequestHeader String macAddress) {
		UserLoginResponse response = userLoginService.login(macAddress);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/api/signup/user")
	public ResponseEntity<UserSignUpResponse> signUp(@RequestHeader String macAddress) {
		UserSignUpResponse response = userLoginService.signUpToUser(macAddress);
		return ResponseEntity.ok(response);
	}
}

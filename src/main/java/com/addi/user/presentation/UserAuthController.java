package com.addi.user.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.addi.user.application.AuthService;
import com.addi.user.application.dto.LoginResponse;
import com.addi.user.application.dto.UserSignUpResponse;
import com.addi.user.doamin.Guardian;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@RestController
@Transactional
@RequiredArgsConstructor
public class UserAuthController {

	private final AuthService userAuthService;

	@GetMapping("/api/login")
	public ResponseEntity<LoginResponse> login(@RequestHeader String macAddress) {
		LoginResponse response = userAuthService.login(macAddress);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/api/signup/user")
	public ResponseEntity<UserSignUpResponse> signUpUser(@RequestHeader String macAddress) {
		UserSignUpResponse response = userAuthService.signUpToUser(macAddress);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/api/signup/guardian")
	public ResponseEntity<Guardian> signUpGuardian(
		@RequestHeader String macAddress,
		@RequestParam String invitationCode
	) {
		log.warn("{}", invitationCode);
		Guardian guardian = userAuthService.signUpToGuardian(macAddress, invitationCode);
		return ResponseEntity.ok(guardian);
	}
}

package com.addi.user.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.addi.user.application.UserLoginService;
import com.addi.user.application.dto.UserLoginResponse;

import lombok.RequiredArgsConstructor;

@RestController
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserFindController {

	private final UserLoginService userSignUpService;

	@GetMapping("/api/login")
	public ResponseEntity<UserLoginResponse> getUserRole(@RequestHeader String macAddress) {
		UserLoginResponse response = userSignUpService.login(macAddress);
		return ResponseEntity.ok(response);
	}
}

package com.addi.user.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.addi.user.application.UserSignUpService;
import com.addi.user.application.dto.UserSignUpStatusResponse;

import lombok.RequiredArgsConstructor;

@RestController
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserFindController {

	private final UserSignUpService userSignUpService;

	@GetMapping("/api/status")
	public ResponseEntity<UserSignUpStatusResponse> existUser(@RequestBody String macAddress) {
		UserSignUpStatusResponse response = userSignUpService.toSignUpStatusResponse(macAddress);
		return ResponseEntity.ok(response);
	}
}

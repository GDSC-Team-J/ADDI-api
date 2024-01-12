package com.addi.user.application;

import static java.util.UUID.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.addi.global.exception.BusinessException;
import com.addi.user.application.dto.LoginResponse;
import com.addi.user.application.dto.UserSignUpResponse;
import com.addi.user.doamin.Guardian;
import com.addi.user.doamin.User;
import com.addi.user.doamin.constants.UserRole;
import com.addi.user.exception.UserError;
import com.addi.user.infra.persistence.GuardianRepository;
import com.addi.user.infra.persistence.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

	private final UserRepository userRepository;
	private final GuardianRepository guardianRepository;

	public LoginResponse login(String macAddress) {
		if (isExistUser(macAddress)) {
			return new LoginResponse(UserRole.USER);
		} else if (isExistGuardian(macAddress)) {
			return new LoginResponse(UserRole.GUARDIAN);
		} else {
			return new LoginResponse(UserRole.NONE);
		}
	}

	public UserSignUpResponse signUpToUser(String macAddress) {
		validateAlreadySignUp(macAddress);
		String invitationCode = generateInvitationCode();
		User user = User.createUser(macAddress, invitationCode);

		userRepository.save(user);
		return new UserSignUpResponse(invitationCode);
	}

	public Guardian signUpToGuardian(
		String macAddress,
		String invitationCode
	) {
		validateAlreadySignUp(macAddress);
		User user = userRepository.findByInvitationCode(invitationCode)
			.orElseThrow(() -> BusinessException.of(UserError.INVITATION_CODE_NOT_FOUND));
		Guardian guardian = Guardian.createGuardian(macAddress, user);
		return guardianRepository.save(guardian);
	}

	private String generateInvitationCode() {
		return randomUUID().toString().substring(0, 6);
	}

	private boolean isExistUser(String macAddress) {
		return userRepository.findByMacAddress(macAddress).isPresent();
	}

	private boolean isExistGuardian(String macAddress) {
		return guardianRepository.findByMacAddress(macAddress).isPresent();
	}

	private boolean isAlreadySignUp(String macAddress) {
		return isExistGuardian(macAddress) || isExistUser(macAddress);
	}

	private void validateAlreadySignUp(String macAddress) {
		if (isAlreadySignUp(macAddress))
			throw BusinessException.of(UserError.ALREADY_SIGN_UP);
	}
}

package com.addi.user.application;

import static java.util.UUID.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.addi.global.exception.BusinessException;
import com.addi.user.application.dto.UserLoginResponse;
import com.addi.user.application.dto.UserSignUpResponse;
import com.addi.user.doamin.User;
import com.addi.user.doamin.constants.UserRole;
import com.addi.user.exception.UserError;
import com.addi.user.infra.persistence.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserLoginService {

	private final UserRepository userRepository;

	public UserLoginResponse login(String macAddress) {
		User user = userRepository.findByMacAddress(macAddress)
			.orElseThrow(() -> BusinessException.of(UserError.NOT_SIGN_UP));

		return new UserLoginResponse(user.getUserRole());
	}

	public UserSignUpResponse signUpToUser(String macAddress) {
		boolean isExist = userRepository.findByMacAddress(macAddress).isPresent();
		if (isExist) {
			throw BusinessException.of(UserError.ALREADY_SIGN_UP);
		}
		String invitationCode = generateInvitationCode();
		User user = new User(UserRole.USER, macAddress, invitationCode);

		userRepository.save(user);
		return new UserSignUpResponse(invitationCode);
	}

	private String generateInvitationCode() {
		return randomUUID().toString().substring(0, 6);
	}
}

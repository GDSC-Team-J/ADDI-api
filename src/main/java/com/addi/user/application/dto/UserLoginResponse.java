package com.addi.user.application.dto;

import com.addi.user.doamin.constants.UserRole;

public record UserLoginResponse(
	UserRole role
) {
}

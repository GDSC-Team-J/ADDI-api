package com.addi.user.doamin;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import com.addi.global.auditing.BaseEntity;
import com.addi.user.doamin.constants.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Enumerated(STRING)
	private UserRole userRole;

	private String macAddress;

	private String invitationCode;

	public User(
		UserRole userRole,
		String macAddress,
		String invitationCode
	) {
		this.userRole = userRole;
		this.macAddress = macAddress;
		this.invitationCode = invitationCode;
	}
}

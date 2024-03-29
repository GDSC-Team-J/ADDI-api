package com.addi.user.doamin;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import com.addi.global.auditing.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "t_user")
@NoArgsConstructor(access = PROTECTED)
public class User extends BaseEntity {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String macAddress;

	private String invitationCode;

	private User(
		String macAddress,
		String invitationCode
	) {
		this.macAddress = macAddress;
		this.invitationCode = invitationCode;
	}

	public static User createUser(
		String macAddress,
		String invitationCode
	) {
		return new User(macAddress, invitationCode);
	}
}

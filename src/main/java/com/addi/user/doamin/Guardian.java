package com.addi.user.doamin;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import com.addi.global.auditing.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_guardian")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Guardian extends BaseEntity {

	@Id
	@Column(name = "guardian_id")
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String macAddress;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	private Guardian(
		String macAddress,
		User user
	) {
		this.macAddress = macAddress;
		this.user = user;
	}

	public static Guardian createGuardian(
		String macAddress,
		User user
	) {
		return new Guardian(macAddress, user);
	}
}


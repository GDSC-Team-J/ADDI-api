package com.addi.user.doamin;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import com.addi.global.auditing.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
}

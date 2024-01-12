package com.addi.analisys.domain;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import com.addi.user.doamin.User;

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
@Table(name = "t_analysis")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Analysis {

	@Id
	@Column(name = "analysis_id")
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String key1;

	private double value1;

	private String key2;

	private double value2;

	private String key3;

	private double value3;

	private String key4;

	private double value4;

	private String key5;

	private double value5;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public Analysis(
		String key1,
		double value1,
		String key2,
		double value2,
		String key3,
		double value3,
		String key4,
		double value4,
		String key5,
		double value5,
		User user
	) {
		this.key1 = key1;
		this.value1 = value1;
		this.key2 = key2;
		this.value2 = value2;
		this.key3 = key3;
		this.value3 = value3;
		this.key4 = key4;
		this.value4 = value4;
		this.key5 = key5;
		this.value5 = value5;
		this.user = user;
	}
}

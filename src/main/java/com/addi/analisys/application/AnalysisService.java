package com.addi.analisys.application;

import static com.addi.analisys.domain.QAnalysis.*;
import static com.addi.user.doamin.QGuardian.*;
import static com.addi.user.doamin.QUser.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.addi.analisys.domain.Analysis;
import com.addi.analisys.infra.persistence.AnalysisRepository;
import com.addi.global.exception.BusinessException;
import com.addi.user.doamin.User;
import com.addi.user.exception.UserError;
import com.addi.user.infra.persistence.GuardianRepository;
import com.addi.user.infra.persistence.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AnalysisService {

	private final JPAQueryFactory factory;
	private final UserRepository userRepository;
	private final GuardianRepository guardianRepository;
	private final AnalysisRepository analysisRepository;

	public Analysis saveAnalysis(
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
		String macAddress
	) {
		User user = userRepository.findByMacAddress(macAddress)
			.orElseThrow(() -> BusinessException.of(UserError.NOT_SIGN_UP));

		Analysis analysis = new Analysis(key1, value1, key2, value2, key3, value3, key4, value4, key5, value5, user);
		return analysisRepository.save(analysis);
	}

	public Analysis findAnalysisByGuardianMacAddress(String macAddress) {
		User foundUser = factory.selectFrom(user)
			.leftJoin(guardian).on(guardian.user.id.eq(user.id))
			.where(guardian.macAddress.eq(macAddress))
			.fetchOne();

		return factory.selectFrom(analysis)
			.leftJoin(user).on(user.id.eq(analysis.user.id))
			.where(analysis.user.id.eq(foundUser.getId()))
			.fetchOne();
	}
}

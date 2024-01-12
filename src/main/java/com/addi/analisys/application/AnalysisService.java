package com.addi.analisys.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.addi.analisys.domain.Analysis;
import com.addi.analisys.infra.persistence.AnalysisRepository;
import com.addi.global.exception.BusinessException;
import com.addi.user.doamin.User;
import com.addi.user.exception.UserError;
import com.addi.user.infra.persistence.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AnalysisService {

	private final UserRepository userRepository;
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
}

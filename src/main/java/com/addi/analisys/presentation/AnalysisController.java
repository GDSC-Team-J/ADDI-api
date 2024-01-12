package com.addi.analisys.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.addi.analisys.application.AnalysisService;
import com.addi.analisys.domain.Analysis;

import lombok.RequiredArgsConstructor;

@RestController
@Transactional
@RequiredArgsConstructor
public class AnalysisController {

	private final AnalysisService analysisService;

	@PostMapping("/api/saveAnalysis")
	public ResponseEntity<Analysis> saveAnalysis(
		@RequestParam String key1,
		@RequestParam double value1,
		@RequestParam String key2,
		@RequestParam double value2,
		@RequestParam String key3,
		@RequestParam double value3,
		@RequestParam String key4,
		@RequestParam double value4,
		@RequestParam String key5,
		@RequestParam double value5,
		@RequestHeader String macAddress
	) {
		Analysis analysis = analysisService.saveAnalysis(
			key1, value1,
			key2, value2,
			key3, value3,
			key4, value4,
			key5, value5,
			macAddress
		);
		return ResponseEntity.ok(analysis);
	}

	@GetMapping("/api/findAnalysis")
	public ResponseEntity<Analysis> findAnalysis(@RequestHeader String macAddress) {
		Analysis analysis = analysisService.findAnalysisByGuardianMacAddress(macAddress);
		return ResponseEntity.ok(analysis);
	}
}

package com.addi.analisys.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.addi.analisys.domain.Analysis;

@Transactional
public interface AnalysisRepository extends JpaRepository<Analysis, Long> {
}

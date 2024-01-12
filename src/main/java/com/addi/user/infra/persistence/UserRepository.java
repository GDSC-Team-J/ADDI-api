package com.addi.user.infra.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.addi.user.doamin.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByMacAddress(String macAddress);
}

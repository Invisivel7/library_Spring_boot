package com.library.library.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library.library.entities.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT s from User s WHERE s.email = ?1")
	Optional<User> findUserByEmail(String email);

}

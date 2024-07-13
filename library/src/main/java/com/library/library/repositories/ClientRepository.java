package com.library.library.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library.library.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	@Query("SELECT s from User s WHERE s.email = ?1")
	Optional<Client> findClientByEmail(String email);

}

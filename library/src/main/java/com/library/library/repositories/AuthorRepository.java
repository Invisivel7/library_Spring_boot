package com.library.library.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library.library.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	@Query("SELECT s from Author s WHERE s.email = ?1")
	Optional<Author> findAuthorByEmail(String email);

}

package com.library.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}

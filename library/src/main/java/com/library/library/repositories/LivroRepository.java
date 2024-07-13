package com.library.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}

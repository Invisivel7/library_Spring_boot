package com.library.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}

package com.library.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}

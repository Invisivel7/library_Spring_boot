package com.library.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.entities.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {

}

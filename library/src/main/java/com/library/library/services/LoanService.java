package com.library.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.library.library.entities.Loan;
import com.library.library.repositories.LoanRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanService {

	private final LoanRepository loanRepository;
	
	public LoanService(LoanRepository loanRepository) {
		this.loanRepository = loanRepository;
	}
	
	public Loan saveLoan(Loan loan) {
		return loanRepository.save(loan);
	}
	
	public List<Loan> getAllLoan(){
		return loanRepository.findAll();
	}
	
	public void deleteLoan(Long id) {
		if(!loanRepository.existsById(id)) {
			throw new Error("Loan with ID " + id + " not found");
		}
		loanRepository.deleteById(id);
	}
	
	public Loan getById(Long id) {
		return loanRepository.findById(id).orElse(null);
	}
	
	public Loan updateLoan(Long id, Loan loan) {
		Optional<Loan> optionalLoan = loanRepository.findById(id);
		if(optionalLoan.isPresent()) {
			Loan existingLoan = optionalLoan.get();
			
			existingLoan.setLivro(loan.getLivro());
			existingLoan.setUser(loan.getUser());
			existingLoan.setClient(loan.getClient());
			existingLoan.setLoan_begin(loan.getLoan_begin());
			existingLoan.setLoan_expiration(loan.getLoan_expiration());
			
			
			return loanRepository.save(existingLoan);
		}
		return null;
	}
	
}

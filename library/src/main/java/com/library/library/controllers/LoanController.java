package com.library.library.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.entities.Loan;
import com.library.library.services.LoanService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LoanController {
	
	private final LoanService loanService;
	
	public LoanController(LoanService loanService) {
		this.loanService = loanService;
	}

	@PostMapping("/to_loan")
	public Loan saveLoan(@RequestBody Loan loan) {
		return loanService.saveLoan(loan);
	}
	
	@GetMapping("/loans")
	public List<Loan> getAllLoan(){
		return loanService.getAllLoan();
	}
	
	@DeleteMapping("/to_loan/{id}")
	public ResponseEntity<?> deleteLoan(@PathVariable Long id){
		try {
			loanService.deleteLoan(id);
			return new ResponseEntity<>("Loan with ID " + id + "deleted successfully", HttpStatus.OK);
		} catch (Error e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/to_loan/{id}")
	public ResponseEntity<?> getLoanById(@PathVariable Long id){
		Loan loan = loanService.getById(id);
		if(loan == null)return ResponseEntity.notFound().build();
		return ResponseEntity.ok(loan);
	}
	
	@PatchMapping("/to_loan/{id}")
	public ResponseEntity<?> updateLoan(@PathVariable Long id, @RequestBody Loan loan){
		Loan updateLoan = loanService.updateLoan(id, loan);
		if(updateLoan == null)return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		return ResponseEntity.ok(updateLoan);
	}
}

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

import com.library.library.entities.Function;
import com.library.library.services.FunctionService;

@RestController
@RequestMapping("/library")
@CrossOrigin("*")

public class FunctionController {
	
	private final FunctionService functionService;
	
	public FunctionController(FunctionService functionService) {
		this.functionService = functionService;
	}
	
	@PostMapping("/function")
	public Function saveFunction(@RequestBody Function function) {
		return functionService.saveFunction(function);
	}
	
	@GetMapping("/functions")
	public List<Function> getAllFunctions(){
		return functionService.getAllFunction();
	}
	
	@DeleteMapping("/function/{id}")
	public ResponseEntity<?> deleteLivro(@PathVariable Long id){
		try {
			functionService.deleteFunction(id);
			return new ResponseEntity<>("function with ID " + id + "deleted successfully", HttpStatus.OK);
		} catch (Error e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/function/{id}")
	public ResponseEntity<?> getLivroById(@PathVariable Long id){
		Function function = functionService.getById(id);
		if(function == null)return ResponseEntity.notFound().build();
		return ResponseEntity.ok(function);
	}
	
	@PatchMapping("/function/{id}")
	public ResponseEntity<?> updateFunction(@PathVariable Long id, @RequestBody Function function){
		Function updateFunction = functionService.updateFunction(id, function);
		if(updateFunction == null)return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		return ResponseEntity.ok(updateFunction);
	}

}

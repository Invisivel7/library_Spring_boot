package com.library.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.library.library.entities.Function;
import com.library.library.repositories.FunctionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FunctionService {
	private final FunctionRepository functionRepository;
	
	public FunctionService(FunctionRepository functionRepository) {
		this.functionRepository = functionRepository;
	}
	
	public Function saveFunction(Function function) {
		return functionRepository.save(function);
	}
	
	public List<Function> getAllFunction(){
		return functionRepository.findAll();
	}
	
	public void deleteFunction(Long id) {
		if(!functionRepository.existsById(id)) {
			throw new Error("Function with ID " + id + " not found");
		}
		functionRepository.deleteById(id);
	}
	
	public Function getById(Long id) {
		return functionRepository.findById(id).orElse(null);
	}
	
	public Function updateFunction(Long id, Function function) {
		Optional<Function> optionalFunction = functionRepository.findById(id);
		if(optionalFunction.isPresent()) {
			Function existingFunction = optionalFunction.get();
			
			existingFunction.setName(function.getName());
			
			return functionRepository.save(existingFunction);
		}
		return null;
	}
}

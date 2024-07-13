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

import com.library.library.entities.Livro;
import com.library.library.services.LivroService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LivroController {
	
	private LivroService livroService;
	
	public LivroController(LivroService livroService) {
		this.livroService = livroService;
	}
	
	@PostMapping("/book")
	public Livro saveLivro(@RequestBody Livro livro) {
		return livroService.saveLivro(livro);
	}
	
	@GetMapping("/books")
	public List<Livro> getAllLivros(){
		return livroService.getAllLivro();
	}
	
	@DeleteMapping("/book/{id}")
	public ResponseEntity<?> deleteLivro(@PathVariable Long id){
		try {
			livroService.deleteLivro(id);
			return new ResponseEntity<>("book with ID " + id + "deleted successfully", HttpStatus.OK);
		} catch (Error e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<?> getLivroById(@PathVariable Long id){
		Livro livro = livroService.getById(id);
		if(livro == null)return ResponseEntity.notFound().build();
		return ResponseEntity.ok(livro);
	}
	
	@PatchMapping("/book/{id}")
	public ResponseEntity<?> updateLivro(@PathVariable Long id, @RequestBody Livro livro){
		Livro updateLivro = livroService.updateLivro(id, livro);
		if(updateLivro == null)return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		return ResponseEntity.ok(updateLivro);
	}

}

package com.library.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.library.library.entities.Livro;
import com.library.library.repositories.LivroRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LivroService {
	
	private final LivroRepository livroRepository;
	
	public LivroService(LivroRepository livroRepository) {
		this.livroRepository = livroRepository;
	}
	
	public Livro saveLivro(Livro livro) {
		return livroRepository.save(livro);
	}
	
	public List<Livro> getAllLivro(){
		return livroRepository.findAll();
	}
	
	public void deleteLivro(Long id) {
		if(!livroRepository.existsById(id)) {
			throw new Error("Livro with ID " + id + " not found");
		}
		livroRepository.deleteById(id);
	}
	
	public Livro getById(Long id) {
		return livroRepository.findById(id).orElse(null);
	}
	
	public Livro updateLivro(Long id, Livro livro) {
		Optional<Livro> optionalLivro = livroRepository.findById(id);
		if(optionalLivro.isPresent()) {
			Livro existingLivro = optionalLivro.get();
			
			existingLivro.setTitle(livro.getTitle());
			existingLivro.setAuthor(livro.getAuthor());
			existingLivro.setGender(livro.getGender());
			existingLivro.setPage_number(livro.getPage_number());
			existingLivro.setPublish_date(livro.getPublish_date());
			existingLivro.setEditora(livro.getEditora());
			
			return livroRepository.save(existingLivro);
		}
		return null;
	}

}

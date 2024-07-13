package com.library.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.library.library.entities.Author;
import com.library.library.repositories.AuthorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorService {
	
	private final AuthorRepository authorRepository;
	
	public AuthorService(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}
	
	public Author saveAuthor(Author author) {
		return authorRepository.save(author);
	}
	
	public List<Author> getAllAuthor(){
		return authorRepository.findAll();
	}
	
	public void deleteAuthor(Long id) {
		if(!authorRepository.existsById(id)) {
			throw new Error("Author with ID " + id + " not found");
		}
		authorRepository.deleteById(id);
	}
	
	public Author getById(Long id) {
		return authorRepository.findById(id).orElse(null);
	}
	
	public Author updateAuthor(Long id, Author author) {
		Optional<Author> optionalAuthor = authorRepository.findById(id);
		if(optionalAuthor.isPresent()) {
			Author existingAuthor = optionalAuthor.get();
			
			existingAuthor.setName(author.getName());
			existingAuthor.setFull_name(author.getFull_name());
			existingAuthor.setEmail(author.getEmail());
			existingAuthor.setPhone(author.getPhone());
			
			return authorRepository.save(existingAuthor);
		}
		return null;
	}

}

package com.library.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.library.library.entities.User;
import com.library.library.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public List<User> getAllUser(){
		return userRepository.findAll();
	}
	
	public void deleteUser(Long id) {
		if(!userRepository.existsById(id)) {
			throw new Error("User with ID " + id + " not found");
		}
		userRepository.deleteById(id);
	}
	
	public User getById(Long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public User updateUser(Long id, User user) {
		Optional<User> optionalUser = userRepository.findById(id);
		if(optionalUser.isPresent()) {
			User existingUser = optionalUser.get();
			
			existingUser.setName(user.getName());
			existingUser.setFull_name(user.getFull_name());
			existingUser.setEmail(user.getEmail());
			existingUser.setPhone(user.getPhone());
			existingUser.setFunction(user.getFunction());
			
			return userRepository.save(existingUser);
		}
		return null;
	}

}

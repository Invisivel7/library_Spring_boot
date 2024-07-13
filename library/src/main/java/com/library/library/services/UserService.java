package com.library.library.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.library.library.entities.User;
import com.library.library.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User saveUser(User user) {
		//Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
		//if(userOptional.isPresent()) {
		//	throw new IllegalStateException("Email taken");
		//}
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
	
	@Transactional
	public User updateUser(Long id, User user) {
		Optional<User> optionalUser = userRepository.findById(id);
		if(optionalUser.isPresent()) {
			User existingUser = optionalUser.get();
			
			if(user.getName() != null && user.getName().length() > 0 && !Objects.equals(existingUser.getName(), user.getName())) {
				existingUser.setName(user.getName());
			}
			
			existingUser.setEmail(user.getEmail());
			existingUser.setFull_name(user.getFull_name());
			existingUser.setPhone(user.getPhone());
			existingUser.setFunction(user.getFunction());
			
			return userRepository.save(existingUser);
		}
		return null;
	}

}

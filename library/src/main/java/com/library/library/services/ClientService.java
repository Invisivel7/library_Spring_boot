package com.library.library.services;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.library.library.entities.Client;
import com.library.library.repositories.ClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {
	
	private final ClientRepository clientRepository;
	
	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	
	public Client saveClient(Client client) {
		Optional<Client> clientOptional = clientRepository.findClientByEmail(client.getEmail());
		if(clientOptional.isPresent()) {
			throw new IllegalStateException("Email taken");
		}
		return clientRepository.save(client);
	}
	
	public List<Client> getAllClient(){
		return clientRepository.findAll();
	}
	
	public void deleteClient(Long id) {
		if(!clientRepository.existsById(id)) {
			throw new Error("Client with ID " + id + " not found");
		}
		clientRepository.deleteById(id);
	}
	
	public Client getById(Long id) {
		return clientRepository.findById(id).orElse(null);
	}
	
	public Client updateClient(Long id, Client client) {
		Optional<Client> optionalClient = clientRepository.findById(id);
		if(optionalClient.isPresent()) {
			Client existingClient = optionalClient.get();
			
			if(client.getName() != null && client.getName().length() > 0 && !Objects.equals(existingClient.getName(), client.getName())) {
				existingClient.setName(client.getName());
			}
			if(client.getEmail() != null && client.getEmail().length() > 0 && !Objects.equals(existingClient.getEmail(), client.getEmail())) {
				Optional<Client> clientOptional = clientRepository.findClientByEmail(client.getEmail());
				if(clientOptional.isPresent()) {
					throw new IllegalStateException("email taken");
				}
				existingClient.setEmail(client.getEmail());
			}
			existingClient.setFull_name(client.getFull_name());
			existingClient.setPhone(client.getPhone());
			
			return clientRepository.save(existingClient);
		}
		return null;
	}

}

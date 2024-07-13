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

import com.library.library.entities.Client;
import com.library.library.services.ClientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ClientController {
	
	private final ClientService clientService;
	
	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}
	
	@PostMapping("/client")
	public Client saveUser(@RequestBody Client client) {
		return clientService.saveClient(client);
	}
	
	@GetMapping("/clients")
	public List<Client> getAllClients(){
		return clientService.getAllClient();
	}
	
	@DeleteMapping("/client/{id}")
	public ResponseEntity<?> deleteClient(@PathVariable Long id){
		try {
			clientService.deleteClient(id);
			return new ResponseEntity<>("Client with ID " + id + "deleted successfully", HttpStatus.OK);
		} catch (Error e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/client/{id}")
	public ResponseEntity<?> getClientById(@PathVariable Long id){
		Client client = clientService.getById(id);
		if(client == null)return ResponseEntity.notFound().build();
		return ResponseEntity.ok(client);
	}
	
	@PatchMapping("/client/{id}")
	public ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody Client client){
		Client updateClient = clientService.updateClient(id, client);
		if(updateClient == null)return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		return ResponseEntity.ok(updateClient);
	}

}

package com.fantasyLibrary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fantasyLibrary.models.response.Response;
import com.fantasyLibrary.services.ClientService;
import com.fantasyLibrary.services.CloneClientService;


@RestController
public class MainController {
	
	@Autowired
	private ClientService clientService;

	@Autowired
	private CloneClientService cloneClientService;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value = "/books")
	public List<Response> getBooks() {
		List<Response> responses;
		//responses = clientService.getAllBooks();
		//responses = cloneClientService.getAllAuthors();
		responses = cloneClientService.getAllBooks();
		return responses;
	}
}

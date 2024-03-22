package com.Controlnote.controle.de.nota.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Controlnote.controle.de.nota.models.Phone;
import com.Controlnote.controle.de.nota.repository.PhoneRepository;

@RestController
@RequestMapping(value = "/phones")
public class PhoneController {

	@Autowired
	private PhoneRepository repository;

	@GetMapping
	public List<Phone> findAll() {
		List<Phone> result = repository.findAll();
		return result;

	}

}

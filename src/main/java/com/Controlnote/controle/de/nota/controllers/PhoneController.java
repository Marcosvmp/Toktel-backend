package com.Controlnote.controle.de.nota.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Controlnote.controle.de.nota.models.Client;
import com.Controlnote.controle.de.nota.models.Phone;
import com.Controlnote.controle.de.nota.repository.ClientRepository;
import com.Controlnote.controle.de.nota.repository.PhoneRepository;

@RestController
@RequestMapping(value = "/phones")
public class PhoneController {

	@Autowired
	private PhoneRepository phonerepository;

	@Autowired
	private ClientRepository clientRepository;

	@GetMapping
	public List<Phone> findAll() {
		List<Phone> result = phonerepository.findAll();
		return result;

	}

	// @PostMapping
	// public String save(@RequestBody Phone phone) {

	// phonerepository.save(phone);

	// return "SALVO COM SUCESSO!";
	// }

	@GetMapping(value = "/{id}")
	public Phone findById(@PathVariable Long id) {
		Phone result = phonerepository.findById(id).get();
		return result;
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		phonerepository.deleteById(id);
	}

	@PostMapping
	public String save(@RequestBody Phone phone) {

		Optional<Client> optionalClient = clientRepository.findByName(phone.getClient().getName());
		if (optionalClient.isPresent()) {

			phone.setClient(optionalClient.get());
			phonerepository.save(phone);
			return "SALVO COM SUCESSO!";
		} else {
			return "Cliente não encontrado";
		}
	}

}
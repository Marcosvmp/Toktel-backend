package com.Controlnote.controle.de.nota.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Controlnote.controle.de.nota.models.Client;
import com.Controlnote.controle.de.nota.models.Phone;
import com.Controlnote.controle.de.nota.repository.ClientRepository;
import com.Controlnote.controle.de.nota.repository.PhoneRepository;

import jakarta.persistence.ElementCollection;

@RestController
@RequestMapping(value = "/phones")
public class PhoneController {

	@Autowired
	@ElementCollection
	private PhoneRepository phonerepository;

	@Autowired
	private ClientRepository clientRepository;

	@GetMapping
	public List<Phone> findAll() {
		List<Phone> result = phonerepository.findAll();
		return result;

	}

	@GetMapping(value = "/{id}")
	public Phone findById(@PathVariable Long id) {
		Optional<Phone> optionalPhone = phonerepository.findById(id);
		if (optionalPhone.isPresent()) {
			Phone result = phonerepository.findById(id).get();
			return result;
		}
		return null;
	}

	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable Long id) {

		Optional<Phone> optinalPhone = phonerepository.findById(id);
		if (optinalPhone.isPresent()) {

			phonerepository.deleteById(id);
			return "Deletado";
		} else {
			return "Telefone não encontrado";
		}
	}

	// @PostMapping
	// public String save(@RequestBody Phone phone) {
	// if (clientRepository.existsById(phone.getClient().getId())) {

	// phonerepository.save(phone);

	// return "to aqui dentro";
	// } else {

	// return "to aqui";
	// }

	// Optional<Client> optionalClient =
	// clientRepository.findById(phone.getClient().getId());
	// if (optionalClient.isPresent()) {

	// phone.setClient(optionalClient.get());
	// phonerepository.save(phone);
	// return "SALVO COM SUCESSO!";
	// } else {
	// return "Cliente não encontrado";
	// }
	// }

	@PostMapping
	public String save(@RequestBody(value = "phones") Phone phones) {
		System.out.println(phones);

		if (clientRepository.existsByCpf(phones.getClient().getCpf())) {
			return "CPF JÁ EXISTE";
		}

		if (clientRepository.existsByEmail(phones.getClient().getEmail())) {
			return "EMAIL JÁ EXISTE";
		}

		if (clientRepository.existsByContact(phones.getClient().getContact())) {
			return "Telefone JÁ EXISTE";
		}

		Client client = clientRepository.save(phones.getClient());

		phones.setClient(client);
		phonerepository.save(phones);

		return "tudo certo";
	}

}
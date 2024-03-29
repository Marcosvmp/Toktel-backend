package com.Controlnote.controle.de.nota.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ResponseStatusException;

import com.Controlnote.controle.de.nota.models.Client;
//import com.Controlnote.controle.de.nota.models.Phone;
import com.Controlnote.controle.de.nota.repository.ClientRepository;
import com.Controlnote.controle.de.nota.repository.PhoneRepository;

//import jakarta.persistence.ElementCollection;
//import jakarta.persistence.OneToMany;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    PhoneRepository phoneRepository;

    @PostMapping
    public String save(@RequestBody Client client) {

        clientRepository.save(client);

        return "SALVO COM SUCESSO!";
    }

    @GetMapping
    public List<Client> findAll() {
        List<Client> result = clientRepository.findAll();
        return result;
    }

    @GetMapping(value = "/{id}")
    public Client findById(@PathVariable Long id) {
        Client result = clientRepository.findById(id).get();
        return result;
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);

        if (optionalClient.isPresent()) {
            clientRepository.deleteById(id);
            return "Cliente deletado com sucesso!";
        } else {
            return "Cliente não encontrado";
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Client clientDetails) {

        if (clientRepository.existsById(id)) {

            Client client = clientRepository.findById(id).get();

            client.setName(clientDetails.getName());
            client.setEmail(clientDetails.getEmail());
            client.setContact(clientDetails.getContact());
            client.setCpf(clientDetails.getCpf());

            clientRepository.save(client);

            return ResponseEntity.ok("Cliente atualizado com sucesso!");
        }

        else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<?> findByCpf(@PathVariable String cpf) {
        Optional<Client> optionalClient = clientRepository.findByCpf(cpf);
        if (optionalClient.isPresent()) {
            return ResponseEntity.ok(optionalClient.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente com o CPF " + cpf + " não encontrado.");
        }
    }

}

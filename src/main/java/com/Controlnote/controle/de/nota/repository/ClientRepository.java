package com.Controlnote.controle.de.nota.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Controlnote.controle.de.nota.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Boolean existsByEmail(String email);

    Boolean existsByContact(String contact);

    Boolean existsByCpf(String cpf);

    Optional<Client> findByCpf(String cpf);

}

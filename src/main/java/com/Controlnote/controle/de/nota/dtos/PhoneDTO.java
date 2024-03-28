package com.Controlnote.controle.de.nota.dtos;

import java.util.ArrayList;

import com.Controlnote.controle.de.nota.models.Client;
import com.Controlnote.controle.de.nota.models.Phone;

public record PhoneDTO(ArrayList<Phone> phones, Client client) {

}

package com.microlending.micro_lending_system.service;

import com.microlending.micro_lending_system.dto.ClientDTO;
import com.microlending.micro_lending_system.model.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {

    List<ClientDTO> getAllActiveClients();
    ClientDTO getClientById(Long id);
    ClientDTO saveClient(ClientDTO clientDTO);
    ClientDTO updateClient(Long id,ClientDTO clientDTO);
    void deleteClientLogically(Long id);
}

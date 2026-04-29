package com.microlending.micro_lending_system.service;

import com.microlending.micro_lending_system.dto.ClientDTO;
import com.microlending.micro_lending_system.exception.AlreadyExistsException;
import com.microlending.micro_lending_system.exception.NotFoundException;
import com.microlending.micro_lending_system.mapper.Mapper;
import com.microlending.micro_lending_system.model.Client;
import com.microlending.micro_lending_system.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService implements IClientService{
    @Autowired
    private IClientRepository repo;

    @Autowired
    private Mapper mapper;

    @Override
    public ClientDTO getClientById(Long id) {
        return repo.findById(id).map(mapper::toDTO).orElseThrow(()->new NotFoundException("Cliente con ID " + id + " no encontrado"));
    }

    @Override
    public List<ClientDTO> getAllActiveClients() {
        return repo.findByActiveTrue().stream().map(mapper::toDTO).toList();
    }

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        repo.findByTaxId(clientDTO.getTaxId()).ifPresent(c->{throw new AlreadyExistsException("ERROR: El cliente con RFC: "+clientDTO.getTaxId()+" ya existe");});

        String[] nameParts=clientDTO.getFullName().split(" ",2);

        Client client=Client.builder()
                .firstName(nameParts[0])
                .lastName(nameParts.length>1?nameParts[1]:"")
                .taxId(clientDTO.getTaxId())
                .email(clientDTO.getEmail())
                .active(true).build();

        Client savedClient=repo.save(client);

        return mapper.toDTO(savedClient);
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        Client client=repo.findById(id).orElseThrow(()->new NotFoundException("No se puede actualizar: Cliente no Encontrado"));

        String[] nameParts=clientDTO.getFullName().split(" ",2);

        client.setFirstName(nameParts[0]);
        client.setLastName(nameParts.length>1?nameParts[1]:"");
        client.setTaxId(clientDTO.getTaxId());
        client.setEmail(clientDTO.getEmail());

        return mapper.toDTO(repo.save(client));
    }

    @Override
    public void deleteClientLogically(Long id) {
        repo.findById(id).ifPresentOrElse(c->{c.setActive(false);repo.save(c);},()->{throw new NotFoundException("Cliente No Existente");});
    }
}

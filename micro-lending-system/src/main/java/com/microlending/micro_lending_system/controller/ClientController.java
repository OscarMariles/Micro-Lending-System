package com.microlending.micro_lending_system.controller;

import com.microlending.micro_lending_system.dto.ClientDTO;
import com.microlending.micro_lending_system.service.IClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @PostMapping
    public ResponseEntity<ClientDTO> saveClient(@Valid @RequestBody ClientDTO clientDTO){
        ClientDTO created=clientService.saveClient(clientDTO);

        return ResponseEntity.created(URI.create("/api/clients/"+created.getId())).body(created);
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllActive(){
        return ResponseEntity.ok(clientService.getAllActiveClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @Valid  @RequestBody ClientDTO clientDTO){
        return ResponseEntity.ok(clientService.updateClient(id,clientDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        clientService.deleteClientLogically(id);
        return ResponseEntity.noContent().build();
    }
}

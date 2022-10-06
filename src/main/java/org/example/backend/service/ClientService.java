package org.example.backend.service;

import org.example.backend.model.Client;
import org.example.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @PreAuthorize("hasAuthority('CAN_WRITE')")
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @PreAuthorize("hasAuthority('CAN_READ')")
    public Iterable<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return clientRepository
                .findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException(name));
    }

}

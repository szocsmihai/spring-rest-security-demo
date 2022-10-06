package org.example.backend.service;

import org.example.backend.model.Role;
import org.example.backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @PreAuthorize("hasAuthority('CAN_WRITE')")
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @PreAuthorize("hasAuthority('CAN_READ')")
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

}

package org.example.backend.service;

import org.example.backend.model.Privilege;
import org.example.backend.repository.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeService {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @PreAuthorize("hasAuthority('CAN_WRITE')")
    public Privilege save(Privilege privilege) {
        return privilegeRepository.save(privilege);
    }

    @PreAuthorize("hasAuthority('CAN_READ')")
    public Iterable<Privilege> findAll() {
        return privilegeRepository.findAll();
    }

}

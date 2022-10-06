package org.example.backend.controller;

import org.example.backend.service.ClientService;
import org.example.backend.service.PrivilegeService;
import org.example.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PrivilegeService privilegeService;

    @GetMapping("")
    public ResponseEntity<String> handleRootRequest() {
        return ResponseEntity.ok("OK");
    }

    @GetMapping("client-confirmation")
    public ResponseEntity<String> handleClientRequest() {
        return ResponseEntity.ok("OK");
    }

    @GetMapping("admin-confirmation")
    public ResponseEntity<String> handleAdminRequest() {
        return ResponseEntity.ok("OK");
    }

    @GetMapping("clients")
    public ResponseEntity<String> handleClientsRequest() {
        StringBuilder builder = new StringBuilder();

        clientService.findAll().forEach(client ->
                builder
                        .append("{ id: ")
                        .append(client.getId())
                        .append(", name: ")
                        .append(client.getName())
                        .append(", password: ")
                        .append(client.getPassword())
                        .append(", role: ")
                        .append(client.getRole().getName())
                        .append(" }, "));

        return ResponseEntity.ok(builder.toString());
    }

    @GetMapping("roles")
    public ResponseEntity<String> handleRolesRequest() {
        StringBuilder builder = new StringBuilder();

        roleService.findAll().forEach(role ->
                builder
                        .append("{ id: ")
                        .append(role.getId())
                        .append(", name: ")
                        .append(role.getName())
                        .append(" }, "));

        return ResponseEntity.ok(builder.toString());
    }

    @GetMapping("privileges")
    public ResponseEntity<String> handlePrivilegesRequest() {
        StringBuilder builder = new StringBuilder();

        privilegeService.findAll().forEach(privilege ->
                builder
                        .append("{ id: ")
                        .append(privilege.getId())
                        .append(", name: ")
                        .append(privilege.getName())
                        .append(" }, "));

        return ResponseEntity.ok(builder.toString());
    }

}

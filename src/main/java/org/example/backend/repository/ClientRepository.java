package org.example.backend.repository;

import org.example.backend.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    // C.R.U.D. operations - inherited from CrudRepository

    Optional<Client> findByName(String name);

}

package org.example.backend.repository;

import org.example.backend.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {

    // C.R.U.D. operations - inherited from CrudRepository

    Optional<Privilege> findByName(String name);

}

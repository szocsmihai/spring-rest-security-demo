package org.example.backend.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "privileges")
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, columnDefinition = "INT")
    private int id;

    @NotBlank(message = "Name cannot be blank")
    @Column(name = "name", nullable = false, unique = true, columnDefinition = "VARCHAR(25)")
    private String name;

    public Privilege() {
    }

    public Privilege(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

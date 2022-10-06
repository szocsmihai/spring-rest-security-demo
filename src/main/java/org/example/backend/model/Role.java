package org.example.backend.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, columnDefinition = "INT")
    private int id;

    //    @NotBlank(message = "Name cannot be blank")
    @Pattern(regexp = "ROLE_\\w+")
    @Column(name = "name", nullable = false, unique = true, columnDefinition = "VARCHAR(25)")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private Set<Privilege> privileges;

    public Role() {
    }

    public Role(String name, Privilege... privileges) {
        this.name = name;
        this.privileges = Stream.of(privileges).collect(Collectors.toSet());
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

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }

}

package org.example.backend.model;

import org.hibernate.validator.constraints.Range;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, columnDefinition = "INT")
    private int id;

    @NotBlank(message = "Name cannot be blank")
    @Column(name = "name", nullable = false, unique = true, columnDefinition = "VARCHAR(50)")
    private String name;

    @NotBlank(message = "Password cannot be blank")
    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(100)")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    /**
     * -----------------------------------------------------------------------
     * VAL    | DESCRIPTION   | DISABLED  | LOCKED    | EXP_ACC   | EXP_CRED
     * -----------------------------------------------------------------------
     * 1      | ACTIVE        | False     | False     | False     | False
     * 2      | DISABLED      | True      | False     | False     | False
     * 3      | LOCKED        | False     | True      | False     | False
     * 4      | EXP_ACC       | False     | False     | True      | False
     * 5      | EXP_CRED      | False     | False     | False     | True
     */
    @Range(min = 1, max = 5)
    @Column(name = "status", nullable = false, columnDefinition = "INT DEFAULT 1")
    private int status;

    public Client() {
    }

    public Client(String name, String password, Role role) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.status = 1;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        role.getPrivileges().forEach(privilege ->
                authorities.add(new SimpleGrantedAuthority(privilege.getName())));

        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return status != 2;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status != 3;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status != 4;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status != 5;
    }

}

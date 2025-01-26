package org.unibl.ip.ip.models.entities;

import jakarta.persistence.*;
import lombok.*;
import org.unibl.ip.ip.models.enums.Role;

@Data
@Entity
@Table(name = "coordinator")
public class CoordinatorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer idCoordinator;
    @Basic
    @Column(name = "name", nullable = false)
    private String name;
    @Basic
    @Column(name = "surname", nullable = false)
    private String surname;
    @Basic
    @Column(name = "username", nullable = false)
    private String username;
    @Basic
    @Column(name = "password", nullable = false)
    private String password;
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition = "ENUM('ADMIN', 'CONSULTANT')", nullable = false)
    private Role role;

}

package org.unibl.ip.ip.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.unibl.ip.ip.models.enums.Role;

import java.io.Serializable;

@Data
public class Coordinator implements Serializable {

    private Integer idCoordinator;

    private String name;

    private String surname;

    private String username;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private Role role;
}

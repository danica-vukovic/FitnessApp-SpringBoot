package org.unibl.ip.ip.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.unibl.ip.ip.models.enums.Role;

@Data
public class CoordinatorRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private Role role;
}

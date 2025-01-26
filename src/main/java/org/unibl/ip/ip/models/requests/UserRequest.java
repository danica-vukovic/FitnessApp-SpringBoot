package org.unibl.ip.ip.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.unibl.ip.ip.models.enums.Status;

@Data
public class UserRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String city;
    @NotBlank
    private String email;
    @NotBlank
    private String avatar;
    @NotBlank
    private Status status;
}

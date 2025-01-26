package org.unibl.ip.ip.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class PasswordRequest {
    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;
}

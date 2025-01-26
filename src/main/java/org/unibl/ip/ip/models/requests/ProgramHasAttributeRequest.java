package org.unibl.ip.ip.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProgramHasAttributeRequest {
    @NotNull
    private Integer programId;
    @NotNull
    private Integer attributeId;
    @NotBlank
    private String value;
}
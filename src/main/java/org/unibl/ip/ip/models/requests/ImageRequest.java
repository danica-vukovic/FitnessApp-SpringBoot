package org.unibl.ip.ip.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.unibl.ip.ip.models.entities.ProgramEntity;

@Data
public class ImageRequest {
    @NotBlank
    private String url;
    @NotNull
    private Integer programId;
}

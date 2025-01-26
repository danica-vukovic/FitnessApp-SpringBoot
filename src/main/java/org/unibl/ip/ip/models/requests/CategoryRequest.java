package org.unibl.ip.ip.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.unibl.ip.ip.models.entities.AttributeEntity;
import org.unibl.ip.ip.models.entities.ProgramEntity;
import org.unibl.ip.ip.models.entities.UserHasCategoryEntity;

import java.util.List;
@Data
public class CategoryRequest {
    @NotBlank
    private String name;
    @NotNull
    private Integer programId;

}

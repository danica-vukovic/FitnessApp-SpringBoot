package org.unibl.ip.ip.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.unibl.ip.ip.models.entities.CategoryEntity;
import org.unibl.ip.ip.models.enums.Type;

@Data
public class AttributeRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String value;
    @NotBlank
    private Type type;
    @NotBlank
    private Integer categoryId;
}

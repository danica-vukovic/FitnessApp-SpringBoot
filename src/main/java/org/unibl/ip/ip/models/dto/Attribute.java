package org.unibl.ip.ip.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.unibl.ip.ip.models.entities.CategoryEntity;
import org.unibl.ip.ip.models.enums.Type;

import java.io.Serializable;

@Data
public class Attribute implements Serializable {

    private Integer idAttribute;

    private String name;

    @JsonIgnore
    private Category category;
}

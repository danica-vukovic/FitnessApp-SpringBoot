package org.unibl.ip.ip.models.dto;

import lombok.Data;
import org.unibl.ip.ip.models.entities.AttributeEntity;
import org.unibl.ip.ip.models.entities.ProgramEntity;
import org.unibl.ip.ip.models.entities.UserHasCategoryEntity;

import java.io.Serializable;
import java.util.List;

@Data
public class Category implements Serializable {

    private Integer idCategory;

    private String name;
}

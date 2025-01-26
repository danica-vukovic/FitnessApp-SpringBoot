package org.unibl.ip.ip.models.dto;

import lombok.Data;
import org.unibl.ip.ip.models.entities.CategoryEntity;
import org.unibl.ip.ip.models.entities.UserEntity;

import java.io.Serializable;

@Data
public class UserHasCategory implements Serializable {

    private Integer id;

    private User user;

    private Category category;
}

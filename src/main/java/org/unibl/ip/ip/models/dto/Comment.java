package org.unibl.ip.ip.models.dto;

import lombok.Data;
import org.unibl.ip.ip.models.entities.ProgramEntity;
import org.unibl.ip.ip.models.entities.UserEntity;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class Comment implements Serializable {

    private Integer idComment;

    private String content;

    private Timestamp dateTime;

    private User user;

    private Program program;
}

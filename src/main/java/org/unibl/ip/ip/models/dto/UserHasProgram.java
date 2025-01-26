package org.unibl.ip.ip.models.dto;

import lombok.Data;
import org.unibl.ip.ip.models.entities.ProgramEntity;
import org.unibl.ip.ip.models.entities.UserEntity;

import java.io.Serializable;
import java.sql.Date;

@Data
public class UserHasProgram implements Serializable {

    private Integer id;

    private Boolean isFinished;

    private Date startDate;

    private Program program;

    private User user;
}

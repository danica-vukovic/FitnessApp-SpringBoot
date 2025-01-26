package org.unibl.ip.ip.models.dto;

import lombok.Data;
import org.unibl.ip.ip.models.entities.UserEntity;
import org.unibl.ip.ip.models.enums.ExerciseType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@Data
public class ActivityLog implements Serializable {

    private Integer idActivityLog;

    private ExerciseType exerciseType;

    private Integer duration;

    private String result;

    private String intensity;

    private Date date;

    private BigDecimal bodyWeight;

    private User user;
}

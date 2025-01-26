package org.unibl.ip.ip.models.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.unibl.ip.ip.models.entities.UserEntity;
import jakarta.validation.constraints.NotBlank;
import org.unibl.ip.ip.models.enums.ExerciseType;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class ActivityLogRequest {
    @NotBlank
    private ExerciseType exerciseType;
    @NotNull
    private Integer duration;
    @NotBlank
    private String result;
    @NotBlank
    private String intensity;
    @NotNull
    private BigDecimal bodyWeight;
    @NotNull
    private Integer userId;
}

package org.unibl.ip.ip.models.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.unibl.ip.ip.models.entities.ProgramEntity;
import org.unibl.ip.ip.models.entities.UserEntity;

import java.sql.Date;

@Data
public class UserHasProgramRequest {
    @NotNull
    private Integer programId;
    @NotNull
    private Integer userId;
}

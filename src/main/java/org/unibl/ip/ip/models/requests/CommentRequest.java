package org.unibl.ip.ip.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.unibl.ip.ip.models.entities.ProgramEntity;
import org.unibl.ip.ip.models.entities.UserEntity;

import java.sql.Timestamp;
@Data
public class CommentRequest {
    @NotBlank
    private String content;
    @NotNull
    private Integer userId;
    @NotNull
    private Integer programId;
}


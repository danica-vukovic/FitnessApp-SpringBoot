package org.unibl.ip.ip.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.unibl.ip.ip.models.entities.UserEntity;

@Data
public class MessageRequest {
    @NotBlank
    private String content;
    @NotNull
    private Integer userId;
    @NotNull
    private Integer receiverId;
    @NotNull
    private Boolean toConsultant;

}

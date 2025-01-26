package org.unibl.ip.ip.models.dto;

import lombok.Data;
import org.unibl.ip.ip.models.entities.UserEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Message implements Serializable {

    private Integer idMessage;

    private String content;

    private Boolean isRead;

    private User user;

    private Integer idReceiver;

    private LocalDateTime sentDate;

    private Boolean toConsultant;
}

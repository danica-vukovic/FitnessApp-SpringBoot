package org.unibl.ip.ip.models.dto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.unibl.ip.ip.models.enums.Status;


@Data
public class LoginResponse {

    private Integer idUser;

    private String name;

    private String surname;

    private String username;

    private String city;

    private String email;

    private String avatar;

    private Status status;

    private String token;
}


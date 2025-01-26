package org.unibl.ip.ip.models.dto;

import lombok.Data;
import org.unibl.ip.ip.models.entities.*;
import org.unibl.ip.ip.models.enums.Status;

import java.io.Serializable;
import java.util.List;

@Data
public class User implements Serializable {

    private Integer idUser;

    private String name;

    private String surname;

    private String username;

    private String password;

    private String city;

    private String email;

    private String avatar;

    private Status status;

   /* private List<ActivityLog> activitylogs;

    private List<Comment> comments;

    private List<Message> messages;

    private List<Program> programs;

    private List<UserHasCategory> userHasCategories;

    private List<UserHasProgram> userHasPrograms;*/
}

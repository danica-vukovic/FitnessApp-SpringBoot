package org.unibl.ip.ip.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.unibl.ip.ip.models.enums.Status;

import java.util.List;

@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer idUser;
    @Basic
    @Column(name = "name", nullable = false)
    private String name;
    @Basic
    @Column(name = "surname", nullable = false)
    private String surname;
    @Basic
    @Column(name = "username", nullable = false)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, length = 500)
    private String password;
    @Basic
    @Column(name = "city", nullable = false)
    private String city;
    @Basic
    @Column(name = "email",nullable = false)
    private String email;
    @Basic
    @Column(name = "avatar")
    private String avatar;
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "ENUM('ACTIVE', 'REQUESTED', 'INACTIVE')")
    private Status status;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<ActivityLogEntity> activitylogs;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<CommentEntity> comments;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<MessageEntity> messages;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<ProgramEntity> programs;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserHasCategoryEntity> userHasCategories;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserHasProgramEntity> userHasPrograms;

}

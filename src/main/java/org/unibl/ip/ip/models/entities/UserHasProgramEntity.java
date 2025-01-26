package org.unibl.ip.ip.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Data
@Entity
@Table(name = "user_has_program")
public class UserHasProgramEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "is_finished", nullable = false)
    private Boolean isFinished;
    @Basic
    @Column(name = "start_date", nullable = false)
    private Date startDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id_program", referencedColumnName = "id", nullable = false)
    private ProgramEntity program;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_user", referencedColumnName = "id", nullable = false)
    private UserEntity user;

}

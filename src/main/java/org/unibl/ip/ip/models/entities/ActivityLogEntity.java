package org.unibl.ip.ip.models.entities;

import jakarta.persistence.*;
import lombok.*;
import org.unibl.ip.ip.models.enums.ExerciseType;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@Entity
@Table(name = "activitylog")
public class ActivityLogEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer idActivityLog;
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "exercise_type", columnDefinition = "ENUM('CARDIO', 'STRENGTH', 'FLEXIBILITY', 'HIIT')", nullable = false)
    private ExerciseType exerciseType;
    @Basic
    @Column(name = "duration", nullable = false)
    private Integer duration;
    @Basic
    @Column(name = "result", nullable = false)
    private String result;
    @Basic
    @Column(name = "intensity", nullable = false)
    private String intensity;
    @Basic
    @Column(name = "date", nullable = false)
    private Date date;
    @Basic
    @Column(name = "body_weight", nullable = false)
    private BigDecimal bodyWeight;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_user", referencedColumnName = "id", nullable = false)
    private UserEntity user;

}

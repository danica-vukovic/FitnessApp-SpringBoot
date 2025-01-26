package org.unibl.ip.ip.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.unibl.ip.ip.models.enums.Level;
import org.unibl.ip.ip.models.enums.Location;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "program")
public class ProgramEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer idProgram;
    @Basic
    @Column(name = "name",  nullable = false)
    private String name;
    @Basic
    @Column(name = "description",  nullable = false)
    private String description;
    @Basic
    @Column(name = "price",  nullable = false)
    private BigDecimal price;
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "level",  nullable = false, columnDefinition = "ENUM('BEGINNER', 'INTERMEDIATE', 'ADVANCED')")
    private Level level;
    @Basic
    @Column(name = "duration",  nullable = false)
    private Integer duration;
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "location", nullable = false, columnDefinition =  "ENUM('ONLINE', 'PARK', 'JIM')")
    private Location location;
    @Basic
    @Column(name = "instructor_information", nullable = false )
    private String instructorInformation;
    @Basic
    @Column(name = "contact", nullable = false)
    private String contact;
    @Basic
    @Column(name = "video_url", nullable = false, length = 300)
    private String videoUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id_category", referencedColumnName = "id", nullable = false)
    private CategoryEntity category;
    @OneToMany(mappedBy = "program")
    @JsonIgnore
    private List<CommentEntity> comments;
    @OneToMany(mappedBy = "program")
    @JsonIgnore
    private List<ImageEntity> images;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_user", referencedColumnName = "id", nullable = false)
    private UserEntity user;
    @OneToMany(mappedBy = "program")
    @JsonIgnore
    private List<UserHasProgramEntity> userHasPrograms;
    @OneToMany(mappedBy = "program")
    @JsonIgnore
    private List<ProgramHasAttributeEntity> programHasAttributes;

}

package org.unibl.ip.ip.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "category")
public class CategoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer idCategory;
    @Basic
    @Column(name = "name", nullable = false)
    private String name;
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<AttributeEntity> attributes;
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<ProgramEntity> programs;
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<UserHasCategoryEntity> userHasCategories;

}

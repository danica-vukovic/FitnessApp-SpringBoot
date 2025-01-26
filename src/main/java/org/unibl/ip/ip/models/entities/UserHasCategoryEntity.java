package org.unibl.ip.ip.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "user_has_category")
public class UserHasCategoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_user", referencedColumnName = "id", nullable = false)
    private UserEntity user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id_category", referencedColumnName = "id", nullable = false)
    private CategoryEntity category;

}

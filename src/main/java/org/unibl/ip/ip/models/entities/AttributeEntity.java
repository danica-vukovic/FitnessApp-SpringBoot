package org.unibl.ip.ip.models.entities;

import jakarta.persistence.*;
import lombok.*;
import org.unibl.ip.ip.models.enums.Type;

@Data
@Entity
@Table(name = "attribute")
public class AttributeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer idAttribute;
    @Basic
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id_category", referencedColumnName = "id", nullable = false)
    private CategoryEntity category;

}

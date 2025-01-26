package org.unibl.ip.ip.models.entities;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "attributevalue")
public class AttributeValueEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "value", nullable = false, length = 255)
    private String value;
    @ManyToOne
    @JoinColumn(name = "attribute_id_attribute", referencedColumnName = "id", nullable = false)
    private AttributeEntity attribute;
}

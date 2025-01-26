package org.unibl.ip.ip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.ip.ip.models.entities.AttributeEntity;
import org.unibl.ip.ip.models.entities.AttributeValueEntity;

import java.util.List;

public interface AttributeValueEntityRepository extends JpaRepository<AttributeValueEntity, Integer> {
    List<AttributeValueEntity> getAllByAttributeIdAttribute(Integer categoryId);
}

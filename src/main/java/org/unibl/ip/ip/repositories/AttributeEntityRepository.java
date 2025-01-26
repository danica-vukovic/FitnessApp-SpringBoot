package org.unibl.ip.ip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.ip.ip.models.entities.AttributeEntity;

import java.util.List;

public interface AttributeEntityRepository extends JpaRepository<AttributeEntity, Integer> {
    List<AttributeEntity> getAllByCategoryIdCategory(Integer categoryId);
}

package org.unibl.ip.ip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.ip.ip.models.entities.ImageEntity;

public interface ImageEntityRepository extends JpaRepository<ImageEntity, Integer> {
}

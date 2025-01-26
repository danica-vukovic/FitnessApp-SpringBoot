package org.unibl.ip.ip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.ip.ip.models.entities.ImageEntity;
import org.unibl.ip.ip.models.entities.ProgramHasAttributeEntity;

import java.util.List;

public interface ProgramHasAttributeEntityRepository extends JpaRepository<ProgramHasAttributeEntity, Integer> {
    List<ProgramHasAttributeEntity> getAllAttributesByProgramIdProgram(Integer id);
}

package org.unibl.ip.ip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.ip.ip.models.entities.ProgramEntity;

import java.util.List;

public interface ProgramEntityRepository extends JpaRepository<ProgramEntity, Integer> {
    public List<ProgramEntity> getAllByUserIdUser(Integer id);
    public List<ProgramEntity> getAllByCategoryIdCategory(Integer id);
}

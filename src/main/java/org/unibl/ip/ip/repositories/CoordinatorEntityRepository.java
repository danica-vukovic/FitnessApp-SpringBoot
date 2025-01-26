package org.unibl.ip.ip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.ip.ip.models.entities.CoordinatorEntity;
import org.unibl.ip.ip.models.enums.Role;

import java.util.List;

public interface CoordinatorEntityRepository extends JpaRepository<CoordinatorEntity, Integer> {
    List<CoordinatorEntity> findByRole(Role role);
}

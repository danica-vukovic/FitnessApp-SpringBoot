package org.unibl.ip.ip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.ip.ip.models.dto.ActivityLog;
import org.unibl.ip.ip.models.entities.ActivityLogEntity;

import java.util.List;

public interface ActivityLogEntityRepository extends JpaRepository<ActivityLogEntity, Integer> {

    List<ActivityLogEntity> getAllByUserIdUser(Integer id);
}

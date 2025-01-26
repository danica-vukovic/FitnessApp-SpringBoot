package org.unibl.ip.ip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.ip.ip.models.dto.Program;
import org.unibl.ip.ip.models.entities.UserHasProgramEntity;

import java.util.List;

public interface UserHasProgramEntityRepository extends JpaRepository<UserHasProgramEntity, Integer> {
    boolean existsByUserIdUserAndProgramIdProgram(Integer userId, Integer programId);
    List<UserHasProgramEntity> getAllByUserIdUser(Integer userId);
    UserHasProgramEntity findByUserIdUserAndProgramIdProgram(Integer userid, Integer programId);
}

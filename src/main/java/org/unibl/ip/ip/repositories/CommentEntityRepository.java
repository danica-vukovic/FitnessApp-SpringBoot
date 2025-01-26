package org.unibl.ip.ip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.ip.ip.models.entities.CommentEntity;

import java.util.List;

public interface CommentEntityRepository extends JpaRepository<CommentEntity, Integer> {
    public List<CommentEntity> getAllByProgramIdProgram(Integer id);
}

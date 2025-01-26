package org.unibl.ip.ip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.unibl.ip.ip.models.dto.Message;
import org.unibl.ip.ip.models.entities.MessageEntity;

import java.util.List;

public interface MessageEntityRepository extends JpaRepository<MessageEntity, Integer> {
    @Query("SELECT m FROM MessageEntity m WHERE (m.user.idUser = :userId AND m.idReceiver = :receiverId) OR (m.user.idUser = :receiverId AND m.idReceiver = :userId)")
    List<MessageEntity> findMessagesBetweenUsers(@Param("userId") Integer userId, @Param("receiverId") Integer receiverId);
}

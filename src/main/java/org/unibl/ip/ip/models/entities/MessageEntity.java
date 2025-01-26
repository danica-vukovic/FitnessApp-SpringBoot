package org.unibl.ip.ip.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "message")
public class MessageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer idMessage;
    @Basic
    @Column(name = "content", nullable = false, length = 1024)
    private String content;
    @Basic
    @Column(name = "is_read", nullable = false)
    private Boolean isRead;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_user", referencedColumnName = "id", nullable = false)
    private UserEntity user;
    @Column(name = "receiver_id", nullable = false)
    private Integer idReceiver;
    @Column(name = "sent_date", nullable = false)
    private LocalDateTime sentDate;
    @Basic
    @Column(name = "to_consultant", nullable = false)
    private Boolean toConsultant;

}

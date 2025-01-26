package org.unibl.ip.ip.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "image")
public class ImageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer idImage;
    @Basic
    @Column(name = "url", nullable = false, length = 1024)
    private String url;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id_program", referencedColumnName = "id", nullable = false)
    private ProgramEntity program;

}

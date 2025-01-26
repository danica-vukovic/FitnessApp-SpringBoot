package org.unibl.ip.ip.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.unibl.ip.ip.models.entities.ProgramEntity;

import java.io.Serializable;

@Data
public class Image implements Serializable {

    private Integer idImage;

    private String url;

    @JsonIgnore
    private Program program;
}

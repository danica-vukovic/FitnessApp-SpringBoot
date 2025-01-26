package org.unibl.ip.ip.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.unibl.ip.ip.models.entities.AttributeEntity;

import java.io.Serializable;

@Data
public class AttributeValue implements Serializable {

    private Integer id;

    private String value;

    @JsonIgnore
    private Attribute attribute;
}

package org.unibl.ip.ip.models.dto;

import lombok.Data;

@Data
public class ProgramHasAttribute {
    private Integer id;

    private Program program;

    private Attribute attribute;

    private String value;
}


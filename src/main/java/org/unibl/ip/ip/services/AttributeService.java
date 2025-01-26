package org.unibl.ip.ip.services;

import org.unibl.ip.ip.models.dto.Attribute;

import java.util.List;

public interface AttributeService {
    List<Attribute> getAllByCategoryId(Integer id);
}

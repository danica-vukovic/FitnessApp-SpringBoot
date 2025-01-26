package org.unibl.ip.ip.services;

import org.unibl.ip.ip.models.dto.AttributeValue;

import java.util.List;

public interface AttributeValueService {
    List<AttributeValue> getAllByAttributeId(Integer id);
}

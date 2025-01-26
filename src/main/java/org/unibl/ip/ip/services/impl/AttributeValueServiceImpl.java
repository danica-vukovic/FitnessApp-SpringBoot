package org.unibl.ip.ip.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.unibl.ip.ip.models.dto.AttributeValue;
import org.unibl.ip.ip.repositories.AttributeValueEntityRepository;
import org.unibl.ip.ip.services.AttributeValueService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttributeValueServiceImpl implements AttributeValueService {
    private final ModelMapper modelMapper;
    private final AttributeValueEntityRepository attributeValueEntityRepository;

    public AttributeValueServiceImpl(ModelMapper modelMapper, AttributeValueEntityRepository attributeValueEntityRepository) {
        this.modelMapper=modelMapper;
        this.attributeValueEntityRepository=attributeValueEntityRepository;
    }
    @Override
    public List<AttributeValue> getAllByAttributeId(Integer id) {
        return attributeValueEntityRepository.getAllByAttributeIdAttribute(id).stream().map(a->modelMapper.map(a, AttributeValue.class)).collect(Collectors.toList());

    }
}

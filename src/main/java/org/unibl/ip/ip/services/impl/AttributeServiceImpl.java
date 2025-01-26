package org.unibl.ip.ip.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.unibl.ip.ip.models.dto.Attribute;
import org.unibl.ip.ip.repositories.AttributeEntityRepository;
import org.unibl.ip.ip.services.AttributeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttributeServiceImpl implements AttributeService {
    private final ModelMapper modelMapper;
    private final AttributeEntityRepository attributeEntityRepository;

    public AttributeServiceImpl(ModelMapper modelMapper, AttributeEntityRepository attributeEntityRepository) {
        this.modelMapper = modelMapper;
        this.attributeEntityRepository = attributeEntityRepository;
    }

    @Override
    public List<Attribute> getAllByCategoryId(Integer id) {
        return attributeEntityRepository.getAllByCategoryIdCategory(id).stream().map(a->modelMapper.map(a, Attribute.class)).collect(Collectors.toList());
    }
}

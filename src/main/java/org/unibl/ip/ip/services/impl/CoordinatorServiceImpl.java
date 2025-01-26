package org.unibl.ip.ip.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.unibl.ip.ip.models.dto.Coordinator;
import org.unibl.ip.ip.models.entities.CoordinatorEntity;
import org.unibl.ip.ip.models.enums.Role;
import org.unibl.ip.ip.repositories.CoordinatorEntityRepository;
import org.unibl.ip.ip.services.CoordinatorService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoordinatorServiceImpl implements CoordinatorService {
    private final ModelMapper modelMapper;
    private final CoordinatorEntityRepository coordinatorEntityRepository;

    @Autowired
    public CoordinatorServiceImpl(ModelMapper modelMapper, CoordinatorEntityRepository coordinatorEntityRepository) {
        this.modelMapper = modelMapper;
        this.coordinatorEntityRepository = coordinatorEntityRepository;
    }

    @Override
    public List<Coordinator> getAll(Authentication auth) {
        List<CoordinatorEntity> coordinatorEntities = coordinatorEntityRepository.findByRole(Role.CONSULTANT);

        return coordinatorEntities.stream()
                .map(entity -> modelMapper.map(entity, Coordinator.class))
                .collect(Collectors.toList());
    }
}

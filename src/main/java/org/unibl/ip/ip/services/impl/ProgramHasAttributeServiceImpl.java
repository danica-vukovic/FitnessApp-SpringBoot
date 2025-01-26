package org.unibl.ip.ip.services.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.unibl.ip.ip.exceptions.NotFoundException;
import org.unibl.ip.ip.models.dto.JwtUser;
import org.unibl.ip.ip.models.dto.ProgramHasAttribute;
import org.unibl.ip.ip.models.entities.ProgramHasAttributeEntity;
import org.unibl.ip.ip.models.requests.ProgramHasAttributeRequest;
import org.unibl.ip.ip.repositories.ProgramHasAttributeEntityRepository;
import org.unibl.ip.ip.services.ProgramHasAttributeService;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ProgramHasAttributeServiceImpl implements ProgramHasAttributeService{
    private final ModelMapper modelMapper;
    private final ProgramHasAttributeEntityRepository programHasAttributeEntityRepository;
    @PersistenceContext
    private EntityManager entityManager;
    private final Logger logger = LogManager.getLogger(ProgramHasAttributeServiceImpl.class);

    public ProgramHasAttributeServiceImpl(ModelMapper modelMapper, ProgramHasAttributeEntityRepository programHasAttributeEntityRepository) {
        this.modelMapper = modelMapper;
        this.programHasAttributeEntityRepository = programHasAttributeEntityRepository;
    }
    @Override
    public void addAttribute(ProgramHasAttributeRequest programHasAttributeRequest, Authentication authentication) {
        ProgramHasAttributeEntity programHasAttributeEntity = modelMapper.map(programHasAttributeRequest, ProgramHasAttributeEntity.class);

        programHasAttributeEntity.setId(null);

        programHasAttributeEntityRepository.saveAndFlush(programHasAttributeEntity);
        entityManager.refresh(programHasAttributeEntity);

        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        logger.info("Attribute " + programHasAttributeEntity.getValue() + " added by user " + jwtUser.getId());
    }

    @Override
    public ProgramHasAttribute findById(Integer id) {
        return modelMapper.map(programHasAttributeEntityRepository.findById(id).orElseThrow(NotFoundException::new), ProgramHasAttribute.class);
    }
    @Override
    public List<ProgramHasAttribute> getAllAttributesByProgramId(Integer id) {
        List<ProgramHasAttributeEntity> programHasAttributeEntities = programHasAttributeEntityRepository.getAllAttributesByProgramIdProgram(id);
        return programHasAttributeEntities.stream()
                .map(a->modelMapper.map(a, ProgramHasAttribute.class))
                .collect(Collectors.toList());
    }
}

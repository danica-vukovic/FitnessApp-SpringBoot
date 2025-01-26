package org.unibl.ip.ip.services.impl;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.core.config.Scheduled;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.unibl.ip.ip.models.dto.JwtUser;
import org.unibl.ip.ip.models.dto.Program;
import org.unibl.ip.ip.models.entities.*;
import org.unibl.ip.ip.models.requests.ProgramRequest;
import org.unibl.ip.ip.repositories.*;
import org.unibl.ip.ip.services.ProgramService;
import org.unibl.ip.ip.exceptions.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProgramServiceImpl implements ProgramService {

    private final ProgramEntityRepository programEntityRepository;
    private final ImageEntityRepository imageEntityRepository;
    private final EntityManager entityManager;
    private final ModelMapper modelMapper;
    private final Logger logger = LoggerFactory.getLogger(ProgramServiceImpl.class);
    private final CommentEntityRepository commentEntityRepository;
    private final UserHasProgramEntityRepository userHasProgramEntityRepository;
    private final ProgramHasAttributeEntityRepository programHasAttributeEntityRepository;

    public ProgramServiceImpl(ProgramEntityRepository programEntityRepository,
                              ImageEntityRepository imageEntityRepository,
                              EntityManager entityManager,
                              ModelMapper modelMapper,
                              CommentEntityRepository commentEntityRepository,
                              UserHasProgramEntityRepository userHasProgramEntityRepository,
                              ProgramHasAttributeEntityRepository programHasAttributeEntityRepository
    ) {
        this.programEntityRepository = programEntityRepository;
        this.imageEntityRepository = imageEntityRepository;
        this.entityManager = entityManager;
        this.modelMapper = modelMapper;
        this.programHasAttributeEntityRepository = programHasAttributeEntityRepository;
        this.commentEntityRepository = commentEntityRepository;
        this.userHasProgramEntityRepository = userHasProgramEntityRepository;
    }

    @Override
    public Program add(ProgramRequest programRequest, Authentication authentication) {
        ProgramEntity programEntity = modelMapper.map(programRequest, ProgramEntity.class);
        programEntity.setIdProgram(null);
        try {
            programEntity = programEntityRepository.saveAndFlush(programEntity);
            for (ImageEntity imageEntity : programEntity.getImages()) {
                imageEntity.setIdImage(null);
                imageEntity.setProgram(programEntity);
                imageEntityRepository.saveAndFlush(imageEntity);
                entityManager.refresh(imageEntity);
            }
            entityManager.refresh(programEntity);
            Program program = findById(programEntity.getIdProgram());

            JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
            logger.info("Insert program " + programEntity.getIdProgram() + " by user: " + jwtUser.getId());
            return program;
        } catch (DataIntegrityViolationException e) {
            logger.error("Data integrity violation while saving program: " + e.getMessage());
            throw new RuntimeException("Data integrity violation", e);
        } catch (Exception e) {
            logger.error("An error occurred while saving the program: " + e.getMessage());
            throw new RuntimeException("Error occurred while saving program", e);
        }
    }

    @Override
    public Program findById(Integer id) {
        return modelMapper.map(programEntityRepository.findById(id).orElseThrow(NotFoundException::new), Program.class);
    }

    @Override
    public List<Program> getAllPrograms() {
        return programEntityRepository.findAll().stream().map(a -> modelMapper.map(a, Program.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteProgram(Integer programId, Authentication authentication) {
        ProgramEntity programEntity = programEntityRepository.findById(programId).orElseThrow(NotFoundException::new);
        for (ImageEntity imageEntity : programEntity.getImages()) {
            imageEntityRepository.deleteById(imageEntity.getIdImage());
        }
        for (UserHasProgramEntity userHasProgramEntity : programEntity.getUserHasPrograms()) {
            userHasProgramEntityRepository.deleteById(userHasProgramEntity.getId());
        }
        for (ProgramHasAttributeEntity programHasAttribute : programEntity.getProgramHasAttributes()) {
            programHasAttributeEntityRepository.deleteById(programHasAttribute.getId());
        }
        for (CommentEntity commentEntity : programEntity.getComments()) {
            commentEntityRepository.deleteById(commentEntity.getIdComment());
        }

        programEntityRepository.deleteById(programId);
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        logger.info("Program " + programEntity.getIdProgram() + " deleted by user: " + jwtUser.getId());
    }
    @Override
    public List<Program> getProgramsByUserId(Integer userId) {
        List<ProgramEntity> userPrograms = programEntityRepository.getAllByUserIdUser(userId);
        return userPrograms.stream()
                .map(entity -> modelMapper.map(entity, Program.class))
                .collect(Collectors.toList());
    }
}

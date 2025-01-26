package org.unibl.ip.ip.services.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.unibl.ip.ip.models.dto.JwtUser;
import org.unibl.ip.ip.models.dto.Program;
import org.unibl.ip.ip.models.entities.UserHasProgramEntity;
import org.unibl.ip.ip.models.requests.UserHasProgramRequest;
import org.unibl.ip.ip.repositories.ProgramEntityRepository;
import org.unibl.ip.ip.repositories.UserHasProgramEntityRepository;
import org.unibl.ip.ip.services.UserHasProgramService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserHasProgramServiceImpl implements UserHasProgramService {
    private final ModelMapper modelMapper;
    private final UserHasProgramEntityRepository userHasProgramEntityRepository;

    private static final Logger logger = LogManager.getLogger(UserHasProgramServiceImpl.class);
    @PersistenceContext
    private EntityManager entityManager;

    public UserHasProgramServiceImpl(ModelMapper modelMapper, UserHasProgramEntityRepository userHasProgramEntityRepository, ProgramEntityRepository programEntityRepository) {
        this.modelMapper = modelMapper;
        this.userHasProgramEntityRepository = userHasProgramEntityRepository;
    }
    @Override
    public boolean userHasProgram(Integer userId, Integer programId, Authentication authentication) {
        return userHasProgramEntityRepository.existsByUserIdUserAndProgramIdProgram(userId, programId);
    }
    @Override
    public void add(UserHasProgramRequest userHasProgramRequest, Authentication auth) {
        UserHasProgramEntity userHasProgramEntity = modelMapper.map(userHasProgramRequest, UserHasProgramEntity.class);
        userHasProgramEntity.setId(null);
        userHasProgramEntity.setStartDate(Date.valueOf(LocalDate.now()));
        userHasProgramEntity.setIsFinished(false);
        userHasProgramEntity = userHasProgramEntityRepository.saveAndFlush(userHasProgramEntity);
        entityManager.refresh(userHasProgramEntity);
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        logger.info("User " + jwtUser.getId() + " buys program " + userHasProgramEntity.getProgram().getIdProgram());
    }

    @Override
    public List<Program> getPurchasedProgramsByUserId(Integer id, Authentication auth) {
        List<UserHasProgramEntity> userPrograms = userHasProgramEntityRepository.getAllByUserIdUser(id);
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        logger.info("Get all the programs the user has bought " + jwtUser.getId());

        return userPrograms.stream()
                .map(userHasProgram -> (modelMapper.map(userHasProgram.getProgram(), Program.class)
                ))
                .collect(Collectors.toList());
    }

    @Scheduled(cron = "0 0 * * * *")
    public void checkAndUpdateCompletedPrograms() {
        List<UserHasProgramEntity> userHasPrograms = userHasProgramEntityRepository.findAll();
        LocalDate now = LocalDate.now();

        for (UserHasProgramEntity userHasProgram : userHasPrograms) {
            if (userHasProgram.getStartDate() != null) {
                LocalDate startDate = userHasProgram.getStartDate().toLocalDate();

                LocalDate endDate = startDate.plusDays(userHasProgram.getProgram().getDuration());

                if (endDate.isBefore(now) && !userHasProgram.getIsFinished()) {
                    userHasProgram.setIsFinished(true);
                    userHasProgramEntityRepository.save(userHasProgram);
                    logger.info("Program marked as finished for UserHasProgram ID: " + userHasProgram.getId());
                }
            } else {
                logger.info("Start Date is null for UserHasProgram ID: " + userHasProgram.getId());
            }
        }
    }

    @Override
    public boolean userFinishedProgram(Integer userId, Integer programId, Authentication auth) {
        return userHasProgramEntityRepository.findByUserIdUserAndProgramIdProgram(userId,programId).getIsFinished();
    }

}


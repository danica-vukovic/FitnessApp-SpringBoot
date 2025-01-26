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
import org.unibl.ip.ip.exceptions.NotFoundException;
import org.unibl.ip.ip.models.dto.JwtUser;
import org.unibl.ip.ip.models.dto.UserHasCategory;
import org.unibl.ip.ip.models.entities.ProgramEntity;
import org.unibl.ip.ip.models.entities.UserHasCategoryEntity;
import org.unibl.ip.ip.models.requests.UserHasCategoryRequest;
import org.unibl.ip.ip.repositories.ProgramEntityRepository;
import org.unibl.ip.ip.repositories.UserHasCategoryEntityRepository;
import org.unibl.ip.ip.services.MailService;
import org.unibl.ip.ip.services.UserHasCategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserHasCategoryServiceImpl implements UserHasCategoryService {
    private final ModelMapper modelMapper;
    private final UserHasCategoryEntityRepository userHasCategoryEntityRepository;
    private final MailService mailService;
    private final ProgramEntityRepository programEntityRepository;
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger logger = LogManager.getLogger(UserHasCategoryServiceImpl.class);

    public UserHasCategoryServiceImpl(ModelMapper modelMapper, UserHasCategoryEntityRepository userHasCategoryEntityRepository,
                                      MailService mailService, ProgramEntityRepository programEntityRepository) {
        this.modelMapper = modelMapper;
        this.userHasCategoryEntityRepository = userHasCategoryEntityRepository;
        this.mailService = mailService;
        this.programEntityRepository = programEntityRepository;
    }

    @Override
    public UserHasCategory add(UserHasCategoryRequest userHasCategoryRequest, Authentication authentication) {
        UserHasCategoryEntity userHasCategoryEntity = modelMapper.map(userHasCategoryRequest, UserHasCategoryEntity.class);
        userHasCategoryEntity.setId(null);
        userHasCategoryEntity = userHasCategoryEntityRepository.saveAndFlush(userHasCategoryEntity);
        entityManager.refresh(userHasCategoryEntity);
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        logger.info("USER " +jwtUser.getId() + " SUBSCRIBES TO CATEGORY " + userHasCategoryEntity.getCategory().getIdCategory());
        return findById(userHasCategoryEntity.getId());
    }

    @Override
    public void delete(Integer userId, Integer categoryId, Authentication auth) {
        UserHasCategoryEntity userHasCategoryEntity = userHasCategoryEntityRepository
                .findByUserIdUserAndCategoryIdCategory(userId, categoryId);

        userHasCategoryEntityRepository.delete(userHasCategoryEntity);
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        logger.info("User " + jwtUser.getId() + " unsubscribed from category " + categoryId);
    }

    @Override
    public boolean userHasCategory(Integer userId, Integer categoryId, Authentication auth) {
        return userHasCategoryEntityRepository.existsByUserIdUserAndCategoryIdCategory(userId,categoryId);
    }

    @Override
    public UserHasCategory findById(Integer id) {
        return modelMapper.map(userHasCategoryEntityRepository.findById(id).orElseThrow(NotFoundException::new), UserHasCategory.class);
    }

    @Scheduled(cron = "0 0 12 * * ?")
    @Override
    public void sendNotifications() {
        List<UserHasCategoryEntity> userCategories = userHasCategoryEntityRepository.findAll();
        for (UserHasCategoryEntity userHasCategory : userCategories) {
            String mail = userHasCategory.getUser().getEmail();
            String categoryName = userHasCategory.getCategory().getName();
            List<ProgramEntity> programs = programEntityRepository.getAllByCategoryIdCategory(userHasCategory.getCategory().getIdCategory());

            if (!programs.isEmpty()) {
                String programsList = programs.stream()
                        .map(program -> program.getName() + " - " + program.getDescription())
                        .collect(Collectors.joining("\n"));
                String subject = "Daily Update: " + userHasCategory.getCategory().getName();

                String notification = "This is your daily notification for the " + categoryName + " category.\n"
                        + "Current available programs are:\n" + programsList;

                mailService.sendEmail(mail,subject, notification);
            } else {
                String subject = "Daily Update: " + userHasCategory.getCategory().getName();
                String notification = "This is your daily notification for the " + categoryName + " category.\n"
                        + "There are currently no new programs available.";

                mailService.sendEmail(mail,subject, notification);
            }
        }
    }
}

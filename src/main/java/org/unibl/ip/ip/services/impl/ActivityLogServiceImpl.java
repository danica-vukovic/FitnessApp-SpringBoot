package org.unibl.ip.ip.services.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.unibl.ip.ip.models.dto.ActivityLog;
import org.unibl.ip.ip.models.dto.JwtUser;
import org.unibl.ip.ip.models.entities.ActivityLogEntity;
import org.unibl.ip.ip.models.requests.ActivityLogRequest;
import org.unibl.ip.ip.repositories.ActivityLogEntityRepository;
import org.unibl.ip.ip.services.ActivityLogService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ActivityLogServiceImpl implements ActivityLogService {
    private static final Logger logger = LogManager.getLogger(ActivityLogServiceImpl.class);
    private final ModelMapper modelMapper;
    private final ActivityLogEntityRepository activityLogEntityRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public ActivityLogServiceImpl(ModelMapper modelMapper, ActivityLogEntityRepository activityLogEntityRepository, EntityManager entityManager) {
        this.modelMapper = modelMapper;
        this.activityLogEntityRepository = activityLogEntityRepository;
        this.entityManager = entityManager;
    }
    @Override
    public List<ActivityLog> getAllActivityLogsByUserId(Integer id, Authentication authentication) {
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        logger.info("Get all activity logs  by user id: " + jwtUser.getId());
        return activityLogEntityRepository.getAllByUserIdUser(id).stream().map(a->modelMapper.map(a,ActivityLog.class)).collect(Collectors.toList());
    }
    @Override
    public void add(ActivityLogRequest activityLogRequest, Authentication authentication) {
        ActivityLogEntity activityLogEntity = modelMapper.map(activityLogRequest, ActivityLogEntity.class);
        activityLogEntity.setIdActivityLog(null);
        activityLogEntity.setDate(Date.valueOf(LocalDate.now()));
        activityLogEntity = activityLogEntityRepository.saveAndFlush(activityLogEntity);
        entityManager.refresh(activityLogEntity);
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        logger.info("Insert activity log " + activityLogEntity.getIdActivityLog() + " by user: " + jwtUser.getId());
    }
}

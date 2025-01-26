package org.unibl.ip.ip.services.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.unibl.ip.ip.models.dto.Comment;
import org.unibl.ip.ip.models.dto.JwtUser;
import org.unibl.ip.ip.models.entities.CommentEntity;
import org.unibl.ip.ip.models.requests.CommentRequest;
import org.unibl.ip.ip.repositories.CommentEntityRepository;
import org.unibl.ip.ip.services.CommentService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final ModelMapper modelMapper;
    private final CommentEntityRepository commentEntityRepository;
    private final Logger logger = LogManager.getLogger(CommentServiceImpl.class);
    @PersistenceContext
    private EntityManager entityManager;

    public CommentServiceImpl(ModelMapper modelMapper, CommentEntityRepository commentEntityRepository) {
        this.modelMapper = modelMapper;
        this.commentEntityRepository = commentEntityRepository;
    }

    @Override
    public List<Comment> getAllByProgramId(Integer id) {
        return commentEntityRepository.getAllByProgramIdProgram(id).stream().map(a->modelMapper.map(a, Comment.class)).collect(Collectors.toList());
    }

    @Override
    public void add(CommentRequest commentRequest, Authentication authentication) {
        CommentEntity commentEntity = modelMapper.map(commentRequest, CommentEntity.class);
        commentEntity.setIdComment(null);
        commentEntity.setDateTime(Timestamp.valueOf(LocalDateTime.now()));
        commentEntity = commentEntityRepository.saveAndFlush(commentEntity);
        entityManager.refresh(commentEntity);
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        logger.info("Add comment " + commentEntity.getIdComment() + " by user: " + jwtUser.getId());
    }


}

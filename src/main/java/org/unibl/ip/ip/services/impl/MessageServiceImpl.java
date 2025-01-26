package org.unibl.ip.ip.services.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.unibl.ip.ip.models.dto.JwtUser;
import org.unibl.ip.ip.models.dto.Message;
import org.unibl.ip.ip.models.dto.Program;
import org.unibl.ip.ip.models.entities.MessageEntity;
import org.unibl.ip.ip.models.entities.UserEntity;
import org.unibl.ip.ip.models.requests.MessageRequest;
import org.unibl.ip.ip.repositories.MessageEntityRepository;
import org.unibl.ip.ip.repositories.UserEntityRepository;
import org.unibl.ip.ip.services.MessageService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class MessageServiceImpl implements MessageService {
    private final MessageEntityRepository messageEntityRepository;
    private final UserEntityRepository userEntityRepository;
    private final ModelMapper modelMapper;
    private static final Logger logger = LogManager.getLogger(MessageServiceImpl.class);
    @PersistenceContext
    private EntityManager entityManager;

    public MessageServiceImpl(MessageEntityRepository messageEntityRepository, UserEntityRepository userEntityRepository, ModelMapper modelMapper) {
        this.messageEntityRepository = messageEntityRepository;
        this.modelMapper = modelMapper;
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public void sendMessage(MessageRequest messageRequest, Authentication authentication) {
        UserEntity sender = userEntityRepository.findById(messageRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Sender not found"));

        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setIdMessage(null);
        messageEntity.setContent(messageRequest.getContent());
        messageEntity.setIsRead(false);
        messageEntity.setUser(sender);
        messageEntity.setToConsultant(messageRequest.getToConsultant());
        messageEntity.setSentDate(LocalDateTime.now());
        messageEntity.setIdReceiver(messageRequest.getReceiverId());
        messageEntityRepository.saveAndFlush(messageEntity);
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        logger.info("User " + jwtUser.getId() + " sent message successfully to user ID: " + messageRequest.getReceiverId());
    }
    @Override
    public List<Message> getMessagesBetweenUsers(Integer userId, Integer receiverId) {
        List<MessageEntity> messageEntities = messageEntityRepository.findMessagesBetweenUsers(userId, receiverId);

        return messageEntities.stream()
                .map(entity -> modelMapper.map(entity, Message.class))
                .collect(Collectors.toList());
    }
}

package org.unibl.ip.ip.services.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.unibl.ip.ip.models.dto.JwtUser;
import org.unibl.ip.ip.models.dto.User;
import org.unibl.ip.ip.models.entities.UserEntity;
import org.unibl.ip.ip.models.enums.Status;
import org.unibl.ip.ip.models.requests.PasswordRequest;
import org.unibl.ip.ip.models.requests.UserRequest;
import org.unibl.ip.ip.repositories.UserEntityRepository;
import org.unibl.ip.ip.services.AuthService;
import org.unibl.ip.ip.services.UserService;
import org.unibl.ip.ip.models.requests.SignUpRequest;
import org.unibl.ip.ip.exceptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;                                                                         ;

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserEntityRepository userEntityRepository, PasswordEncoder passwordEncoder,
                           AuthService authService) {
        this.modelMapper = modelMapper;
        this.userEntityRepository = userEntityRepository;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    @Override
    public void signUp(SignUpRequest request) {
        if (userEntityRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists.");
        }

        UserEntity entity = modelMapper.map(request, UserEntity.class);
        logger.info(entity.getUsername(), entity.getName(), entity.getSurname(), entity.getAvatar());
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        entity.setStatus(Status.REQUESTED);

        userEntityRepository.saveAndFlush(entity);

        authService.sendActivationLinkToMail(entity.getUsername(), entity.getEmail());

        logger.info("Signup user: " + entity.getIdUser());

    }
    @Override
    public User findById(Integer id, Authentication authentication) {
        return modelMapper.map(userEntityRepository.findById(id).orElseThrow(NotFoundException::new), User.class);
    }
    @Override
    public List<User> getAll(Authentication auth) {
        return userEntityRepository.findAll().stream().map(a ->modelMapper.map(a, User.class)).collect(Collectors.toList());
    }
    @Override
    public User update(Integer id, UserRequest userRequest, Authentication auth) {
        UserEntity userEntity = modelMapper.map(userRequest,UserEntity.class);
        userEntity.setIdUser(id);
        userEntity.setStatus(userEntityRepository.findById(id).orElseThrow(NotFoundException::new).getStatus());
        userEntity.setUsername(userEntityRepository.findById(id).orElseThrow(NotFoundException::new).getUsername());
        userEntity.setPassword(userEntityRepository.findById(id).orElseThrow(NotFoundException::new).getPassword());
        userEntity = userEntityRepository.saveAndFlush(userEntity);
        entityManager.refresh(userEntity);
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        logger.info("User update: " + jwtUser.getId());
        return findById(userEntity.getIdUser(), auth);
    }

    @Override
    public boolean changePassword(Integer id, PasswordRequest passwordRequest, Authentication authentication){
        UserEntity userEntity = modelMapper.map(userEntityRepository.findById(id),UserEntity.class);
        String oldPasswordEncoded = passwordEncoder.encode(passwordRequest.getOldPassword());
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        if (passwordEncoder.matches(passwordRequest.getOldPassword(), userEntity.getPassword())) {
            userEntity.setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
            userEntity = userEntityRepository.saveAndFlush(userEntity);
            entityManager.refresh(userEntity);
            logger.info("User with ID {} changed password", jwtUser.getId());
            return true;
        }
        logger.info("User password change failed for user ID {}", jwtUser.getId());

        return false;
    }
}


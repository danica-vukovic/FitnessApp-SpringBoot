package org.unibl.ip.ip.services.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.unibl.ip.ip.exceptions.LoggingUtil;
import org.unibl.ip.ip.exceptions.NotFoundException;
import org.unibl.ip.ip.exceptions.UnauthorizedException;
import org.unibl.ip.ip.models.dto.User;
import org.unibl.ip.ip.models.entities.UserEntity;
import org.unibl.ip.ip.models.enums.Status;
import org.unibl.ip.ip.models.requests.LoginRequest;
import org.unibl.ip.ip.repositories.UserEntityRepository;
import org.unibl.ip.ip.services.AuthService;
import org.unibl.ip.ip.models.dto.LoginResponse;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.springframework.security.core.AuthenticationException;


@Service
public class AuthServiceImpl implements AuthService {

    @Value("${authorization.token.expiration-time}")
    private String tokenExpirationTime;
    @Value("${authorization.token.secret}")
    private String tokenSecret;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    private final UserEntityRepository userEntityRepository;
    private final MailServiceImpl mailService;
    private Key key;

    private static final Logger logger = LogManager.getLogger(AuthServiceImpl.class);

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserEntityRepository userEntityRepository, ModelMapper modelMapper, MailServiceImpl mailService) {
        this.authenticationManager = authenticationManager;
        this.userEntityRepository = userEntityRepository;
        this.modelMapper = modelMapper;
        this.mailService = mailService;
    }

    @PostConstruct
    public void init() {
        if (tokenSecret == null || tokenSecret.isEmpty()) {
            throw new IllegalArgumentException("Token secret is not provided");
        }
        this.key = Keys.hmacShaKeyFor(tokenSecret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserEntity userEntity = userEntityRepository.findByUsername(request.getUsername()).orElseThrow(NotFoundException::new);
            if (userEntity.getStatus().equals(Status.ACTIVE)) {
                LoginResponse response = modelMapper.map(userEntity, LoginResponse.class);
                response.setToken(generateJwt(userEntity));
                logger.info("Login user: " + userEntity.getIdUser());
                return response;
            } else {
                sendActivationLinkToMail(userEntity.getUsername(), userEntity.getEmail());
                logger.info("Send activation link for user: " + userEntity.getIdUser());
                return null;
            }
        } catch (AuthenticationException e) {
            logger.warn("Invalid username or password for user: " + request.getUsername());
            throw new UnauthorizedException("Invalid username or password");
        } catch (NotFoundException e) {
            logger.warn("User not found: " + request.getUsername());
            throw new UnauthorizedException("User not found");
        } catch (Exception e) {
            LoggingUtil.logException(e, getClass());
            throw new RuntimeException("Internal server error, please try again later");
        }
    }

    private String generateJwt(UserEntity user) {
        return Jwts.builder()
                .setId(user.getIdUser().toString())
                .setSubject(user.getUsername())
                .claim("role", user.getStatus().name())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(tokenExpirationTime)))
                .signWith(key)
                .compact();
    }

    @Override
    public void sendActivationLinkToMail(String username, String email) {
        String token = generateActivationToken(username);
        String activationLink = "http://localhost:4200/auth/activate?token=" + token;
        mailService.sendEmail(email, "Activate your account", "Click the link to activate your account: " + activationLink);
    }

    private String generateActivationToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(tokenExpirationTime)*2))
                .signWith(key)
                .compact();
    }

    @Override
    public User activateAccount(String token) {
        try {
            Claims claims = parseJWT(token);
            String username = claims.getSubject();

            UserEntity userEntity = userEntityRepository.findByUsername(username).orElseThrow(NotFoundException::new);

                userEntity.setStatus(Status.ACTIVE);
                userEntity = userEntityRepository.saveAndFlush(userEntity);
                logger.info("User activated: " + userEntity.getIdUser());
                return modelMapper.map(userEntity, User.class);

        } catch (Exception e) {
            logger.error("Error during activation", e);
            return null;
        }
    }

    public Claims parseJWT(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }
}

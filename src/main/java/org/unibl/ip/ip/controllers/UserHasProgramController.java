package org.unibl.ip.ip.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.ILoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.unibl.ip.ip.models.dto.UserHasProgram;
import org.unibl.ip.ip.models.requests.UserHasProgramRequest;
import org.unibl.ip.ip.services.UserHasProgramService;
import org.unibl.ip.ip.services.impl.UserHasProgramServiceImpl;

@RequestMapping("/user-has-programs")
@RestController
public class UserHasProgramController {
    private final UserHasProgramService userHasProgramService;

    public UserHasProgramController(UserHasProgramService userHasProgramService) {
        this.userHasProgramService = userHasProgramService;
    }
    @PostMapping
    public ResponseEntity<Void> add(@RequestBody UserHasProgramRequest userHasProgramRequest, Authentication authentication){
        try {
            userHasProgramService.add(userHasProgramRequest, authentication);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}


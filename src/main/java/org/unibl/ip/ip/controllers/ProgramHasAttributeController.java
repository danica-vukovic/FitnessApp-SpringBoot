package org.unibl.ip.ip.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.ip.ip.models.dto.ProgramHasAttribute;
import org.unibl.ip.ip.models.requests.ProgramHasAttributeRequest;
import org.unibl.ip.ip.services.ProgramHasAttributeService;
import org.unibl.ip.ip.services.impl.AuthServiceImpl;

@RestController
@RequestMapping("/program-has-attributes")
public class ProgramHasAttributeController {
    private final ProgramHasAttributeService programHasAttributeService;

    public ProgramHasAttributeController(ProgramHasAttributeService programHasAttributeService) {
        this.programHasAttributeService = programHasAttributeService;
    }

    @PostMapping
    public ResponseEntity<Void> addAttribute(@RequestBody ProgramHasAttributeRequest programHasAttributeRequest, Authentication authentication) {
        try {
            programHasAttributeService.addAttribute(programHasAttributeRequest, authentication);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

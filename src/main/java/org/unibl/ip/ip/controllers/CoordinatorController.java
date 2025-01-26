package org.unibl.ip.ip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.ip.ip.models.dto.Coordinator;
import org.unibl.ip.ip.services.CoordinatorService;

import java.util.List;


@RestController
@RequestMapping("/advisors")
public class CoordinatorController {

    private final CoordinatorService coordinatorService;

    @Autowired
    public CoordinatorController(CoordinatorService coordinatorService) {
        this.coordinatorService = coordinatorService;
    }

    @GetMapping
    public List<Coordinator> getAllAdvisors(Authentication authentication) {
        return coordinatorService.getAll(authentication);
    }
}

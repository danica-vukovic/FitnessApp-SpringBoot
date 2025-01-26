package org.unibl.ip.ip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.ip.ip.models.requests.ActivityLogRequest;
import org.unibl.ip.ip.repositories.ActivityLogEntityRepository;
import org.unibl.ip.ip.services.ActivityLogService;

@RestController
@RequestMapping("/activity-log")
public class ActivityLogController {
    private final ActivityLogService activityLogService;

    public ActivityLogController(ActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody ActivityLogRequest activityLogRequest, Authentication authentication){
        try {
            System.out.println(activityLogRequest);
            activityLogService.add(activityLogRequest, authentication);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}


package org.unibl.ip.ip.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.unibl.ip.ip.models.dto.ActivityLog;
import org.unibl.ip.ip.models.dto.Program;
import org.unibl.ip.ip.models.dto.User;
import org.unibl.ip.ip.models.requests.PasswordRequest;
import org.unibl.ip.ip.models.requests.UserRequest;
import org.unibl.ip.ip.services.ActivityLogService;
import org.unibl.ip.ip.services.ProgramService;
import org.unibl.ip.ip.services.UserHasProgramService;
import org.unibl.ip.ip.services.UserService;
import org.unibl.ip.ip.services.impl.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ProgramService programService;
    private final ActivityLogService activityLogService;
    private final UserHasProgramService userHasProgramService;


    public UserController(UserService userService, UserHasProgramService userHasProgramService,
    ProgramService programService, ActivityLogService activityLogService) {
        this.userService = userService;
        this.userHasProgramService=userHasProgramService;
        this.programService = programService;
        this.activityLogService = activityLogService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id, Authentication authentication) {
        User user = userService.findById(id, authentication);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Integer id, @RequestBody UserRequest userRequest, Authentication auth) {
        return userService.update(id, userRequest, auth);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<String> changePassword(@PathVariable Integer id, @RequestBody PasswordRequest passwordRequest, Authentication authentication) {
        boolean success = userService.changePassword(id, passwordRequest, authentication);
        if (success) {
            return ResponseEntity.ok("Password changed successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password change failed. The current password is incorrect.");
        }
    }

    @GetMapping("/{userId}/programs/{programId}/exists")
    public ResponseEntity<Boolean> userHasProgram(@PathVariable Integer userId, @PathVariable Integer programId, Authentication authentication) {
        boolean hasProgram = userHasProgramService.userHasProgram(userId, programId,authentication);
        return ResponseEntity.ok(hasProgram);
    }

    @GetMapping("/{userId}/my-programs")
    public ResponseEntity<List<Program>> getMyPrograms(@PathVariable Integer userId) {
        List<Program> programs = programService.getProgramsByUserId(userId);
        return ResponseEntity.ok(programs);
    }

    @GetMapping("/{userId}/programs")
    public ResponseEntity<List<Program>> getPurchasedPrograms(@PathVariable Integer userId, Authentication authentication) {
        List<Program> programs = userHasProgramService.getPurchasedProgramsByUserId(userId, authentication);
        return ResponseEntity.ok(programs);
    }

    @GetMapping("/{userId}/user-finished-program/{programId}")
    public boolean userCompletedProgram(@PathVariable Integer userId, @PathVariable Integer programId, Authentication auth) {
        return userHasProgramService.userFinishedProgram(userId, programId, auth);
    }

    @GetMapping("/{id}/activity-log")
    public List<ActivityLog> getAllActivityLogsByUserId(@PathVariable Integer id, Authentication authentication) {
        return activityLogService.getAllActivityLogsByUserId(id, authentication);
    }

    @GetMapping
    public List<User> getAll(Authentication authentication){
        return userService.getAll(authentication);
    }

}

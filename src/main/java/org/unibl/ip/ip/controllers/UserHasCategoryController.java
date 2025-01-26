package org.unibl.ip.ip.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.unibl.ip.ip.models.dto.UserHasCategory;
import org.unibl.ip.ip.models.requests.UserHasCategoryRequest;
import org.unibl.ip.ip.services.UserHasCategoryService;

@RestController
@RequestMapping("/user-has-categories")
public class UserHasCategoryController {
    private final UserHasCategoryService userHasCategoryService;

    public UserHasCategoryController(UserHasCategoryService userHasCategoryService) {
        this.userHasCategoryService = userHasCategoryService;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody UserHasCategoryRequest userHasCategoryRequest, Authentication authentication) {
        try {
            UserHasCategory userHasCategory = userHasCategoryService.add(userHasCategoryRequest, authentication);
            return ResponseEntity.status(HttpStatus.CREATED).body(userHasCategory);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{userId}/{categoryId}")
    public ResponseEntity<Void> delete(@PathVariable Integer userId, @PathVariable Integer categoryId, Authentication authentication) {
        userHasCategoryService.delete(userId, categoryId, authentication);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/{categoryId}")
    public ResponseEntity<Boolean> userHasCategory(@PathVariable Integer userId, @PathVariable Integer categoryId, Authentication authentication) {

        try {
            boolean hasCategory = userHasCategoryService.userHasCategory(userId, categoryId, authentication);

            return ResponseEntity.ok(hasCategory);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(false);
        }
     }
}

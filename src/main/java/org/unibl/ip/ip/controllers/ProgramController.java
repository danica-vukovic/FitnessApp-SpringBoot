package org.unibl.ip.ip.controllers;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.unibl.ip.ip.models.dto.Category;
import org.unibl.ip.ip.models.dto.Comment;
import org.unibl.ip.ip.models.dto.Program;
import org.unibl.ip.ip.models.dto.ProgramHasAttribute;
import org.unibl.ip.ip.models.requests.ProgramRequest;
import org.unibl.ip.ip.services.CommentService;
import org.unibl.ip.ip.services.ProgramHasAttributeService;
import org.unibl.ip.ip.services.ProgramService;
import org.unibl.ip.ip.services.impl.ProgramServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/programs")
public class ProgramController {

    private final ProgramService programService;
    private final CommentService commentService;
    private final ProgramHasAttributeService programHasAttributeService;

    public ProgramController(ProgramService programService, ProgramHasAttributeService programHasAttributeService,
                             CommentService commentService) {
        this.programService = programService;
        this.programHasAttributeService=programHasAttributeService;
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Program> add(@RequestBody @Valid ProgramRequest programRequest, Authentication auth) {
        try {

            Program program = programService.add(programRequest, auth);
            return ResponseEntity.status(HttpStatus.CREATED).body(program);

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Program>> getAllPrograms() {
        List<Program> programs = programService.getAllPrograms();
        return ResponseEntity.ok(programs);
    }

    @DeleteMapping("/{programId}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Integer programId, Authentication authentication) {
        try {
            programService.deleteProgram(programId, authentication);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/attributes")
    public List<ProgramHasAttribute> getAllAttributesByProgramId(@PathVariable Integer id) {
        return programHasAttributeService.getAllAttributesByProgramId(id);

    }
    @GetMapping("/{id}/comments")
    public List<Comment> getAllCommentsByProgramId(@PathVariable Integer id){
        return commentService.getAllByProgramId(id);
    }
}

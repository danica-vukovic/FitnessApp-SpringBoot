package org.unibl.ip.ip.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.unibl.ip.ip.models.dto.Message;
import org.unibl.ip.ip.models.requests.MessageRequest;
import org.unibl.ip.ip.services.MessageService;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody MessageRequest messageRequest, Authentication authentication) {
        try {
            messageService.sendMessage(messageRequest, authentication);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/{userId}/{receiverId}")
    public ResponseEntity<List<Message>> getMessagesBetweenUsers(@PathVariable Integer userId, @PathVariable Integer receiverId) {
        try {
            List<Message> messages = messageService.getMessagesBetweenUsers(userId, receiverId);
            return new ResponseEntity<>(messages, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

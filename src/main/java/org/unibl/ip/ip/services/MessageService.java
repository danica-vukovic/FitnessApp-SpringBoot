package org.unibl.ip.ip.services;

import org.springframework.security.core.Authentication;
import org.unibl.ip.ip.models.dto.Message;
import org.unibl.ip.ip.models.requests.MessageRequest;

import java.util.List;

public interface MessageService {
    void sendMessage(MessageRequest messageRequest, Authentication authentication);

    List<Message> getMessagesBetweenUsers(Integer userId, Integer receiverId);
}

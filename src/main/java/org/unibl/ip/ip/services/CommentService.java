package org.unibl.ip.ip.services;

import org.springframework.security.core.Authentication;
import org.unibl.ip.ip.models.dto.Comment;
import org.unibl.ip.ip.models.requests.CommentRequest;

import java.util.List;

public interface CommentService {
    List<Comment> getAllByProgramId(Integer id);

    void add(CommentRequest commentRequest, Authentication authentication);
}

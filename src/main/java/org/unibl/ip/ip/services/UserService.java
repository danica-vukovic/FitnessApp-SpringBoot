package org.unibl.ip.ip.services;

import org.springframework.security.core.Authentication;
import org.unibl.ip.ip.models.dto.User;
import org.unibl.ip.ip.models.requests.PasswordRequest;
import org.unibl.ip.ip.models.requests.SignUpRequest;
import org.unibl.ip.ip.models.requests.UserRequest;

import java.util.List;

public interface UserService {
    public void signUp(SignUpRequest request);
    User findById(Integer id, Authentication authentication);

    List<User> getAll(Authentication auth);

    User update(Integer id, UserRequest userRequest, Authentication auth);
    boolean changePassword(Integer id, PasswordRequest passwordRequest, Authentication authentication);
}


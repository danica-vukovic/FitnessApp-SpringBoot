package org.unibl.ip.ip.services;

import org.unibl.ip.ip.models.dto.LoginResponse;
import org.unibl.ip.ip.models.dto.User;
import org.unibl.ip.ip.models.requests.LoginRequest;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    void sendActivationLinkToMail(String username, String email);
    User activateAccount(String token);
}

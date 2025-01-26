package org.unibl.ip.ip.services;

import org.springframework.security.core.Authentication;
import org.unibl.ip.ip.models.dto.Program;
import org.unibl.ip.ip.models.requests.UserHasProgramRequest;

import java.util.List;

public interface UserHasProgramService {
    boolean userHasProgram(Integer userId, Integer programId, Authentication authentication);
    void add(UserHasProgramRequest userHasProgramRequest, Authentication auth);

    List<Program> getPurchasedProgramsByUserId(Integer id, Authentication auth);

    boolean userFinishedProgram(Integer userId, Integer programId, Authentication auth);
}

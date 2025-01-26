package org.unibl.ip.ip.services;

import org.springframework.security.core.Authentication;
import org.unibl.ip.ip.models.dto.Program;
import org.unibl.ip.ip.models.requests.ProgramRequest;

import java.util.List;

public interface ProgramService {
    Program add(ProgramRequest programRequest, Authentication authentication);

    Program findById(Integer id);

    List<Program> getAllPrograms();

    void deleteProgram(Integer programId, Authentication authentication);

    List<Program> getProgramsByUserId(Integer userId);
}

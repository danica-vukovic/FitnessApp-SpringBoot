package org.unibl.ip.ip.services;

import org.springframework.security.core.Authentication;
import org.unibl.ip.ip.models.dto.ProgramHasAttribute;
import org.unibl.ip.ip.models.requests.ProgramHasAttributeRequest;

import java.util.List;

public interface ProgramHasAttributeService {
    void addAttribute(ProgramHasAttributeRequest programHasAttributeRequest, Authentication authentication);

    ProgramHasAttribute findById(Integer id);

    List<ProgramHasAttribute> getAllAttributesByProgramId(Integer id);
}

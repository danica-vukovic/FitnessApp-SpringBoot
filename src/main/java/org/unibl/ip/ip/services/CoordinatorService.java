package org.unibl.ip.ip.services;

import org.springframework.security.core.Authentication;
import org.unibl.ip.ip.models.dto.Coordinator;

import java.util.List;

public interface CoordinatorService {
    List<Coordinator> getAll(Authentication auth);
}

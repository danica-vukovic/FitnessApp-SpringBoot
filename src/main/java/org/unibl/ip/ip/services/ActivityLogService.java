package org.unibl.ip.ip.services;

import org.springframework.security.core.Authentication;
import org.unibl.ip.ip.models.dto.ActivityLog;
import org.unibl.ip.ip.models.requests.ActivityLogRequest;

import java.util.List;

public interface ActivityLogService {
    List<ActivityLog> getAllActivityLogsByUserId(Integer id, Authentication authentication);

    void add(ActivityLogRequest activityLogRequest, Authentication authentication);
}

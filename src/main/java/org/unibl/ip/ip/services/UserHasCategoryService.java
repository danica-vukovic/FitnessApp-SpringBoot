package org.unibl.ip.ip.services;

import org.springframework.security.core.Authentication;
import org.unibl.ip.ip.models.dto.UserHasCategory;
import org.unibl.ip.ip.models.requests.UserHasCategoryRequest;

public interface UserHasCategoryService {
    void delete(Integer userId, Integer categoryId, Authentication auth);

    boolean userHasCategory(Integer userId, Integer categoryId, Authentication auth);

    UserHasCategory findById(Integer id);

    UserHasCategory add(UserHasCategoryRequest userHasCategoryRequest, Authentication authentication);

    void sendNotifications();
}

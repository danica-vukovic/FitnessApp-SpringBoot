package org.unibl.ip.ip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.ip.ip.models.entities.UserHasCategoryEntity;

public interface UserHasCategoryEntityRepository extends JpaRepository<UserHasCategoryEntity, Integer> {
    UserHasCategoryEntity findByUserIdUserAndCategoryIdCategory(Integer userId, Integer categoryId);
    boolean existsByUserIdUserAndCategoryIdCategory(Integer userId, Integer categoryId);
}

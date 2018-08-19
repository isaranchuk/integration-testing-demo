package com.isaranchuk.activities.repository;

import com.isaranchuk.activities.model.UserActivity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserActivityRepository extends CrudRepository<UserActivity, String> {
    List<UserActivity> findByUsername(String username);
}

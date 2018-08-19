package com.isaranchuk.activities.api;

import com.isaranchuk.activities.model.Activity;
import com.isaranchuk.activities.model.UserActivity;
import com.isaranchuk.activities.service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/v1/users/{username}/activities/any")
    public ResponseEntity<Activity> findAnyForUser(@PathVariable String username) {
        UserActivity activity = activityService.findAnyForUser(username);
        return ResponseEntity.ok(activity);
    }

    @GetMapping("/v1/users/{username}/activities/history")
    public ResponseEntity<List<UserActivity>> findHistoryForUser(@PathVariable String username) {
        List<UserActivity> activities = activityService.findHistoryForUser(username);
        return ResponseEntity.ok(activities);
    }
}

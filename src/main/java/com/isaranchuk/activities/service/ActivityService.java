package com.isaranchuk.activities.service;

import com.isaranchuk.activities.model.Activity;
import com.isaranchuk.activities.model.UserActivity;
import com.isaranchuk.activities.repository.UserActivityRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ActivityService {

    private static final String GET_ANY_ACTIVITY = "/api/activity";

    private final RestTemplate restTemplate;

    private final UserActivityRepository userActivityRepository;

    public ActivityService(@Value("${activity.base-url}") String baseUrl, RestTemplateBuilder restTemplateBuilder, UserActivityRepository userActivityRepository) {
        this.userActivityRepository = userActivityRepository;
        this.restTemplate = restTemplateBuilder.rootUri(baseUrl).build();
    }

    public UserActivity findAnyForUser(String username) {
        Activity activity = restTemplate.getForObject(GET_ANY_ACTIVITY, Activity.class);

        UserActivity userActivity = UserActivity.of(activity).withUsername(username);
        userActivityRepository.save(userActivity);

        return userActivity;
    }

    public List<UserActivity> findHistoryForUser(String username) {
        return userActivityRepository.findByUsername(username);
    }
}

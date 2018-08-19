package com.isaranchuk.activities.model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Data
@RedisHash("user_activities")
public class UserActivity extends Activity {
    @Indexed
    String username;

    public UserActivity withUsername(String username) {
        this.setUsername(username);
        return this;
    }

    public static UserActivity of(Activity activity) {
        UserActivity userActivity = new UserActivity();
        userActivity.setActivity(activity.getActivity());
        userActivity.setType(activity.getType());
        userActivity.setKey(activity.getKey());

        return userActivity;
    }
}

package com.isaranchuk.activities.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Activity {
    @Id
    private String key;
    private String activity;
    private String type;
}

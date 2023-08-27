package com.example.flowershopbot.properties.webhook_config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FacebookEntry implements Serializable {
    private String id;
    private Long time;
    private List<FacebookMessaging> messaging = new ArrayList<>();

    }


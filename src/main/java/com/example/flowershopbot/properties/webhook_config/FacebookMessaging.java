package com.example.flowershopbot.properties.webhook_config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FacebookMessaging implements Serializable {

    private Map<String,String> sender;
    private Map<String,String> recipient;
    private Long timestamp;
    private FacebookMessage message;
    private FaceBookPostBack postBack;
}

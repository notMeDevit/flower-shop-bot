package com.example.flowershopbot.properties.webhook_config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FaceBookPostBack {
    private String payload;
}

package com.example.flowershopbot.properties.client_msg_config;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Component
public class Messaging_Message {
    private String mid;
    private String text;
}

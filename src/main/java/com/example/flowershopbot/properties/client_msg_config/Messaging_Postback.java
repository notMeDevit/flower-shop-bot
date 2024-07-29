package com.example.flowershopbot.properties.client_msg_config;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Component
public class Messaging_Postback {
    private String title;
    private String payload;
    private String mid;
}
/*
"postback": {
         "title": "Buy a flower arrangment",
         "payload": "yes",
         "mid": "m_z8qhQeBh-YXFL8L2xy7NHS-ynYziU7QqpFp_inqtIHE1FcgFQ8enoTAlZIV8y-3HkZ7pPQWgkbpxBXxAkXL6dQ"
         }
         */
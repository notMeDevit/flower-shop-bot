package com.example.flowershopbot.properties.client_msg_config;

import lombok.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Component
public class Entry_Messaging {

    private Messaging_Sender sender;
    private Messaging_Sender recipient;
    private BigInteger timestamp;

    @Nullable
    private Messaging_Message message;

    @Nullable
    private Messaging_Postback postback;
}

/*
 "postback": {
         "title": "Buy a flower arrangment",
         "payload": "yes",
         "mid": "m_z8qhQeBh-YXFL8L2xy7NHS-ynYziU7QqpFp_inqtIHE1FcgFQ8enoTAlZIV8y-3HkZ7pPQWgkbpxBXxAkXL6dQ"
         }
         */
package com.example.flowershopbot.properties.fbattchment_config;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Component
public class FbMessage {

    private FbAttchmnt attachment;
}

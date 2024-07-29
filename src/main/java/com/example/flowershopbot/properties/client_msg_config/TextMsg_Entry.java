package com.example.flowershopbot.properties.client_msg_config;

import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Component
public class TextMsg_Entry {
    private BigInteger time;
    private String id;
    private ArrayList<Entry_Messaging> messaging = new ArrayList<>();

}

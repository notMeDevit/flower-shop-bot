package com.example.flowershopbot.properties.fbattchment_config;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Component
public class FbAttchmnt {

    private String type;
    private FbPayload payload;

}

/*
* type":"image",
                            "payload":{
                                        "url":"https.your-url.com/image.jp",
                                                                    "is_reusable": true
*
*
* */
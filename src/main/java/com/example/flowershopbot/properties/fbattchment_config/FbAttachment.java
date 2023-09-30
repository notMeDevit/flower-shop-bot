package com.example.flowershopbot.properties.fbattchment_config;

import com.example.flowershopbot.controllers.WebHook;
import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Component
public class FbAttachment implements Serializable {


    private FbRecipient recipient;
    private FbMessage message;
}

 /*       "access_token":"Your_page_access_token",
           "recipient": {
                        "id": "6567177600041771"
    },
        "message":{
            "attachment":{
        "                   type":"image",
                            "payload":{
                                        "url":"https.your-url.com/image.jp",
                                                                    "is_reusable": true
        }
        }
        }
        }*/

/*
* "recipient": {
        "id": "6567177600041771"
    },*/
package com.example.flowershopbot.properties.fb_templates.product_template;

import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Component
public class fb_template_top implements Serializable {

    private fb_recipient recipient;
    private fb_message message;

}

 /* {
        "recipient": {
        "id": "6567177600041771"
    },
        "message": {
        "attachment": {
            "type": "template",
                    "payload": {
                "template_type": "product",
                        "elements": [
                {
                    "id": "7440365599326519"
                },
                {
                    "id":"6768031193253154"
                }
                ]
            }
        }
    }
    }

    */
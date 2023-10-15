package com.example.flowershopbot.properties.fb_templates.button_template.ButtonTemplate;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Component
public class ButtonTemplate {

    private BtnRecipient recipient;
    private BtnMessage message;


}
/*
{
    "recipient": {
        "id": "6567177600041771"
    },
    "message": {
        "attachment": {
            "type": "template",
            "payload": {
                "template_type": "button",
                "text": "What do you want to do next?",
                "buttons": [
                    {
                        "type": "web_url",
                        "url": "https://www.messenger.com",
                        "title": "Visit Messenger"
                    }
                ]
            }
        }
    }
}
*/

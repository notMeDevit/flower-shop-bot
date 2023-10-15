package com.example.flowershopbot.properties.fb_templates.button_template.ButtonTemplate;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Component
public class BtnButtons {
    private String type;
    private String url;
    private String title;
    private String payload;
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
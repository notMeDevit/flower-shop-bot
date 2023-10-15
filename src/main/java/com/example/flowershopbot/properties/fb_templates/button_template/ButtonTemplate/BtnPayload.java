package com.example.flowershopbot.properties.fb_templates.button_template.ButtonTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Component
public class BtnPayload {
    @JsonProperty("template_type")
    private String template_type;

    private String text;
    private ArrayList<BtnButtons> buttons = new ArrayList<>();
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
package com.example.flowershopbot.properties.generic_template;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Component
public class GenericTemp_elements {

    private String title;
    private String image_url;
    private String subtitle;
    private GenericTemp_DefaultAction default_action;
    private ArrayList<GenericTemp_Buttons> buttons;
}
/*{
        "title": "Welcome!",
        "image_url": "https://raw.githubusercontent.com/fbsamples/original-coast-clothing/main/public/styles/male-work.jpg",
        "subtitle": "We have the right hat for everyone.",
        "default_action": {
        "type": "web_url",
        "url": "https://www.originalcoastclothing.com/",
        "webview_height_ratio": "tall"
        },
        "buttons": [
        {
        "type": "web_url",
        "url": "https://www.originalcoastclothing.com/",
        "title": "View Website"
        },
        {
        "type": "postback",
        "title": "Start Chatting",
        "payload": "DEVELOPER_DEFINED_PAYLOAD"
        */

package com.example.flowershopbot.properties.webhook_config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FaceBookElements implements Serializable {


    private String media_type;
    private String attachment_id;
}


/*
"media_type": "<image|video>",
"attachment_id": "<ATTACHMENT_ID>"
 */
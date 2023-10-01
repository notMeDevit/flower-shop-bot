package com.example.flowershopbot.properties.webhook_config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FacebookMessage implements Serializable {

    private String mid;
    private Long seq;

    @Nullable
    private String text;

    //private FaceBookAttachment attachments;
    private ArrayList<FaceBookAttachment> attachments = new ArrayList<>();
}

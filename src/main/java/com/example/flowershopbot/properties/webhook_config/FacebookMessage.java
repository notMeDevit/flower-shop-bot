package com.example.flowershopbot.properties.webhook_config;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
@Builder
@Jacksonized
public class FacebookMessage implements Serializable {
    private String mid;
    private Long seq;

    @JsonSetter(nulls = Nulls.SKIP)
    private String text = "string";
/*
    @JsonSetter(nulls = Nulls.SKIP)
    public void setText(String text) {
        if (text != null) {
            this.text = text;
        }
    }
    */

    //private FaceBookAttachment attachments;
    private ArrayList<FaceBookAttachment> attachments = new ArrayList<>();
}

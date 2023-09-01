package com.example.flowershopbot.properties.attachment_config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FbAttachment {

    public String type;
    public FbPayload payload;
}

package com.example.flowershopbot.properties.fbattchment_config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Component
public class FbPayload {

    private String url;

    @JsonProperty("is_reusable")
    private boolean is_reusable;


}
/*url":"https.your-url.com/image.jp",
        "is_reusable": true
        */

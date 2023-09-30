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
public class fb_attchmnt implements Serializable {

    private String type;
    private fb_payload payload;

}
/*
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

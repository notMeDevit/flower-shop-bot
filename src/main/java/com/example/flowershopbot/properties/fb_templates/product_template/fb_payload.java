package com.example.flowershopbot.properties.fb_templates.product_template;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Component
public class fb_payload implements Serializable {

    @JsonProperty("template_type")
    private String template_type;

    private ArrayList<fb_elements_products> elements = new ArrayList<>();
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
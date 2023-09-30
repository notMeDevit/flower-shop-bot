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
public class fb_recipient implements Serializable {

    private String id;

}

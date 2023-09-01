package com.example.flowershopbot.properties.attachment_config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FbPayload {

    public String template_type;
    public ArrayList<FbElements> elements = new ArrayList<>();
}

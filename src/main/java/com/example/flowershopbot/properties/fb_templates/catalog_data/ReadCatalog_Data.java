package com.example.flowershopbot.properties.fb_templates.catalog_data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReadCatalog_Data implements Serializable {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("retailer_id")
    private String retailer_id;
}
/*{
        "data": [
        {
        "id": "7440365599326519",
        "name": "Flower Arrangment #1",
        "retailer_id": "byy1rmlsry"
        },
        {
        "id": "6768031193253154",
        "name": "Flower Arrangement #2",
        "retailer_id": "e84tn6savj"
        }
        ],
        */
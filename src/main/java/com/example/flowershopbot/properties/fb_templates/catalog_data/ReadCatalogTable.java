package com.example.flowershopbot.properties.fb_templates.catalog_data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReadCatalogTable implements Serializable {
    private ArrayList<ReadCatalog_Data> data = new ArrayList<>();
    @JsonIgnore
    private Object paging;
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

package com.example.flowershopbot.properties.supabase_config;

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
public class ReadTable implements Serializable {

    @JsonProperty("id")
    public int id;

    @JsonProperty("created_at")
    public String created_at;

    @JsonProperty("flower_id")
    public int flower_id;

}

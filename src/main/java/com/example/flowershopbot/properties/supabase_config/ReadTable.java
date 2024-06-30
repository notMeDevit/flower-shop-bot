package com.example.flowershopbot.properties.supabase_config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Component
public class ReadTable implements Serializable {

    @JsonProperty("id")
    public int id;

    @JsonProperty("created_at")
    public String created_at;

    @JsonProperty("flower_id")
    public int flower_id;

}

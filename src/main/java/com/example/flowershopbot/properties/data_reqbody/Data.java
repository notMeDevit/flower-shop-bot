package com.example.flowershopbot.properties.data_reqbody;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class Data {

    private int originalRequestNumber;
}

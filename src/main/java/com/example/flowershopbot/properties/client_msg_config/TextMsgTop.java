package com.example.flowershopbot.properties.client_msg_config;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Component
public class TextMsgTop {

    private String object;
    private ArrayList<TextMsg_Entry> entry = new ArrayList<>();
    //private TextMsg_Entry entry;
    //private ArrayList<FaceBookElements> elements = new ArrayList<>();

}
/*
{
    "object": "page",
    "entry": [
        {
            "time": 1721060534648,
            "id": "110944995430025",
            "messaging": [
                {
                    "sender": {
                        "id": "6567177600041771"
                    },
                    "recipient": {
                        "id": "110944995430025"
                    },
                    "timestamp": 1721060532576,
                    "message": {
                        "mid": "m_mPkPtLgR8Qrp5xWwi4Va0y-ynYziU7QqpFp_inqtIHFjKEvV7_tkFlWy81LWWfPrmRYN3cHaG5Scgki0qpPUAQ",
                        "text": "g"
                    }
                }
            ]
        }
    ]
}
 */
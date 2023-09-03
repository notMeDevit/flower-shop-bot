package com.example.flowershopbot.controllers;

import com.example.flowershopbot.configurations.MessageConfiguration;
import com.example.flowershopbot.properties.supabase_config.ReadTable;
import com.example.flowershopbot.properties.webhook_config.FacebookHookRequest;
import com.example.flowershopbot.properties.webhook_config.FacebookMessageResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.Builder;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.regex.Pattern;
//import com.apple.eawt.Application;
//import com.fasterxml.jackson.*;


@Builder
@RequestMapping(value = "/v1")
@RestController
public class WebHook {

MessageConfiguration messageConfiguration;

    private final String PAGE_TOKEN ="EAAC2kuYox3wBO9sdaE7gtJJu0MKKqKwW87uScCCJNLmXXoZAsqurLSMe7FLeAuJVrYKUrFJEMShxjKbiNWnf88wQNhBZBK6ASXOSQ7xp7FzmhRpgIgi9IctgAfYR2vmteCqCXQSYCjtNyUVNKIIZBn3tZASdEkkP29EnExbL8RQbmGbYmQsXmD4dMu8u9Gps";
    private final String VERIFY_TOKEN="someRandomnString256";
    //this is for reply messages
    private final String FB_MSG_URL="https://graph.facebook.com/v2.6/me/messages?access_token="
            + PAGE_TOKEN;

    //logger to watch what is happening in our bot
    private final Logger logger = LoggerFactory.getLogger(WebHook.class);
    private final RestTemplate template = new RestTemplate();
    private final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    //private final MessageConfiguration messageConfiguration = new MessageConfiguration();


    //This is necessary for register a webhook in facebook
    @GetMapping(value = "/webhook")
    @ResponseStatus(HttpStatus.OK)
    public String get(@RequestParam(name = "hub.verify_token")String token,
                      @RequestParam(name = "hub.challenge")String challenge){
        if(token!=null && !token.isEmpty() && token.equals(VERIFY_TOKEN)){
            return challenge;
        }else{
            return "Wrong Token";
        }
    }

    //This method  reply all messages with: 'This is a test message'
    @PostMapping(value = "/webhook")
    @ResponseStatus(HttpStatus.OK)
    public void post(@RequestBody FacebookHookRequest request) throws JsonProcessingException { //@RequestBody ){


        ObjectMapper mapper = new ObjectMapper();
        String jsonBook = mapper.writeValueAsString(request);
        logger.info("{}",jsonBook);


        logger.info("Message from chat: {}",request);
        //System.out.println(request.toString());

        try {
            request.getEntry().forEach(e -> {
                e.getMessaging().forEach(m -> {

                    if(m.getMessage().getText() == null){
                        if(m.getMessage().getAttachments().getType().equals("template")){
                            System.out.println("true");

                            m.getMessage().getAttachments().getPayload().getElements().forEach(z -> {

                                        String personId = m.getSender().get("id");
                                        String attachmentId = z.getAttachment_id();
                                        System.out.print(attachmentId);

                                        sendReply(personId,"You sent an attachment, id: " + attachmentId);


                                    });
                            /* Refer to  -> if request is attachment,
                            respond with "is this the correct picture?" postback example in Javascript code */
                        }
                    }
                    else {

                        String id = m.getSender().get("id");
                        String userMsg = m.getMessage().getText();
                        boolean isMatch = false;


                        if (isNumeric(userMsg)) {

                            sendReply(id, "Checking Database for flower arrangement");
                            try {
                                isMatch = messageConfiguration.checkDb(Integer.valueOf(userMsg));
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }

                            if (isMatch) {
                                sendReply(id, "Flower Arrangement Matches");

                            } else {
                                sendReply(id, "Flower Arrangement does not match. Please resend flower arrangement id.");
                            }
                            // sendReply("The price is <var> please confirm and then answer picture postback, and then proceed with payment

                        } else if (userMsg.equals("yes") || userMsg.equals("Yes")) {
                            sendReply(id, "Please send the UUID for the flower arrangement");


                        } else {
                            sendReply(id, "Hello would you like to buy a flower arrangement?");
                        }
                    }
                });
            });


        }
        catch (Exception ex){
            logger.info("Exception",ex);
        }

}
/*

 try{
      int final_personId = messageConfiguration.checkDb(Integer.valueOf(userMsg));
 }catch (IOException ex) {
      throw new RuntimeException(ex);
                        }
 */


    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    //save regex checker of numeric expression for later

    public boolean isNumericRegex(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }


    private void sendReply(String id,String text){
        FacebookMessageResponse response = new FacebookMessageResponse();
        response.setMessage_type("text");
        response.getRecipient().put("id",id);
        response.getMessage().put("text",text);

        HttpEntity<FacebookMessageResponse> entity = new HttpEntity<>(response);
        String result = template.postForEntity(FB_MSG_URL,entity,String.class).getBody();

        logger.info("Message result: {}",result);

    }
}

  /*
        Object jsonString = mapper.readValue(request.toString(), Object.class);
        String indentedString = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(jsonString);


        System.out.println(jsonString);
        System.out.println(indentedString);

         */
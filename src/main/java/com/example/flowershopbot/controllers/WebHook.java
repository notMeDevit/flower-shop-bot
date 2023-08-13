package com.example.flowershopbot.controllers;

import com.example.flowershopbot.configurations.MessageConfiguration;
import com.example.flowershopbot.properties.webhookConfig.FacebookHookRequest;
import com.example.flowershopbot.properties.webhookConfig.FacebookMessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.Builder;
import org.springframework.web.client.RestTemplate;
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
    public void post(@RequestBody FacebookHookRequest request){
        logger.info("Message from chat: {}",request);
        request.getEntry().forEach(e->{
            e.getMessaging().forEach(m->{
                String id = m.getSender().get("id");
                sendReply(id,"This is a test message");
            });
        });
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


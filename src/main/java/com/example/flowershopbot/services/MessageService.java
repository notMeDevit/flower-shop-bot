package com.example.flowershopbot.services;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.example.flowershopbot.configurations.MessageConfiguration;
import com.example.flowershopbot.controllers.WebHook;
import com.example.flowershopbot.properties.fb_templates.product_template.*;
import com.example.flowershopbot.properties.fbattchment_config.*;
import com.example.flowershopbot.properties.generic_template.*;
import com.example.flowershopbot.properties.webhook_config.FacebookMessageResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class MessageService {

    MessageConfiguration messageConfiguration;

    private final String PAGE_TOKEN = "EAAC2kuYox3wBO9sdaE7gtJJu0MKKqKwW87uScCCJNLmXXoZAsqurLSMe7FLeAuJVrYKUrFJEMShxjKbiNWnf88wQNhBZBK6ASXOSQ7xp7FzmhRpgIgi9IctgAfYR2vmteCqCXQSYCjtNyUVNKIIZBn3tZASdEkkP29EnExbL8RQbmGbYmQsXmD4dMu8u9Gps";
    private final String VERIFY_TOKEN = "someRandomnString256";
    //this is for reply messages
    private final String FB_MSG_URL = "https://graph.facebook.com/v2.6/me/messages?access_token="
            + PAGE_TOKEN;

    //logger to watch what is happening in our bot
    private final Logger logger = LoggerFactory.getLogger(WebHook.class);
    private final RestTemplate template = new RestTemplate();


    @Autowired
    public MessageService(MessageConfiguration messageConfiguration){
        this.messageConfiguration = messageConfiguration;
    }

    public void botRouteMessage(String person_id, String userMsg) throws IOException {

        // Build Flower-Shop-Template and extract the JSON Object
        String genericTemplateJson = genericTemplateBuilder(person_id);
        String productCatalogTemplate = catalogBuilder(person_id);

        //String attachmentTemplate = attachmentBuilder(person_id,attchmnt,type);
        ArrayList<String> productName = null;

        //Gets an arraylist of product names to check against user selection
        boolean productNameFlag = false;
        ArrayList<String> getProductNames = new ArrayList<>();
        getProductNames = messageConfiguration.getProductId(productNameFlag);

        try {
            productName = messageConfiguration.getProductId(false);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


        if (productName.contains(userMsg)) {

            sendReply(person_id, "Excellent Choice");
        }
        //userMsg.payload
        else if (userMsg.equals("A")) {
            customerPreBuiltService(productCatalogTemplate);

        } else if (userMsg.equals("B")) {
            System.out.println("B");

        } else {
            // Put the JSON Object into the configuration function and send the first message!(Generic Template)
            sendReply(person_id,"Welcome to my Flower shop!");
            logger.info("{}", genericTemplateJson);
            messageConfiguration.sendGenericTemplate(genericTemplateJson);

            }
        }


    public void customerPreBuiltService(String productCatalogTemplate) throws IOException {

        try {
            messageConfiguration.sendProductCarousel(productCatalogTemplate);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        //user responds with product template name


    }

    public boolean userMessageDatabase(boolean isMatch, String userMsg){

        try {
            isMatch = messageConfiguration.checkDb(Integer.valueOf(userMsg));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return isMatch;

    }


    private String jsonStringify(Object jsonObject) throws JsonProcessingException {

        ObjectMapper mapObj = new ObjectMapper();

        return mapObj.writeValueAsString(jsonObject);
    }

    @NotNull
    private String attachmentBuilder(String attachment, String type, String psId) {


        FbPayload fbPayload1 = FbPayload.builder()
                .url(attachment)
                .is_reusable(true)
                .build();
        FbAttchmnt fbAttchmnt1 = FbAttchmnt.builder()
                .type(type)
                .payload(fbPayload1)
                .build();
        FbMessage fbMessage1 = FbMessage.builder()
                .attachment(fbAttchmnt1)
                .build();
        FbRecipient fbRecipient1 = FbRecipient.builder()
                .id(psId)
                .build();
        FbAttachment fbAttachment1 = FbAttachment.builder()
                .recipient(fbRecipient1)
                .message(fbMessage1)
                .build();

        try {
            String finalJson = jsonStringify(fbAttachment1);
            messageConfiguration.sendAttachment(finalJson);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return "Yes";
    }

    @NotNull
    private String genericTemplateBuilder(String psid) {

        ArrayList<GenericTemp_elements> elementsObj = new ArrayList<>()
;
        GenericTemp_DefaultAction gtDA = GenericTemp_DefaultAction.builder()
                .type("web_url")
                .url("https://www.instagram.com/thecabbagerosefloraldesign")
                .webview_height_ratio("tall")
                .build();

        GenericTemp_Buttons gtbObj1 = GenericTemp_Buttons.builder()
                .type("web_url")
                .url("https://www.instagram.com/thecabbagerosefloraldesign")
                .title("View Instagram Page")
                .build();
        GenericTemp_Buttons gtbObj2 = GenericTemp_Buttons.builder()
                .type("postback")
                .title("Pre-Built Arrangement")
                .payload("A")
                .build();
        GenericTemp_Buttons gtbObj3 = GenericTemp_Buttons.builder()
                .type("postback")
                .title("Custom Arrangement")
                .payload("B")
                .build();

        ArrayList<GenericTemp_Buttons> buttons_array = new ArrayList<>();
        buttons_array.add(gtbObj1);
        buttons_array.add(gtbObj2);
        buttons_array.add(gtbObj3);


        GenericTemp_elements gtE = GenericTemp_elements.builder()
                .title("Welcome to my Flower-Shop, me friend!")
                .image_url("https://scontent.xx.fbcdn.net/v/t1.15752-9/372391913_623944406554009_6257077991188984464_n.png?_nc_cat=105&ccb=1-7&_nc_sid=58c789&_nc_ohc=V4GNSGFpug4AX8XWBzc&_nc_ad=z-m&_nc_cid=0&_nc_ht=scontent.xx&oh=03_AdT4apf8_3-J6J7OEMyjo6twpIOwtT8U6bj2IEKXf5jTgg&oe=652D61E2")
                .subtitle("We fill all your flower needs!")
                .default_action(gtDA)
                .buttons(buttons_array)
                .build();

        elementsObj.add(gtE);


        GenericTemp_Payload gtP = GenericTemp_Payload.builder()
                .template_type("generic")
                .elements(elementsObj)
                .build();

        GenericTemp_Attachment gtA = GenericTemp_Attachment.builder()
                .type("template")
                .payload(gtP)
                .build();

        GenericTemp_Message gtM = GenericTemp_Message.builder()
                .attachment(gtA)
                .build();

        GenericTemp_Recipient gtR = GenericTemp_Recipient.builder()
                .id(psid)
                .build();

        Top_GenericTemp topGenericTemp = Top_GenericTemp.builder()
                .message(gtM)
                .recipient(gtR)
                .build();
        String finalJson = "";
        try {
            finalJson = jsonStringify(topGenericTemp);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }

        return finalJson;
    }

    private String catalogBuilder(String personId){


        ArrayList<String> product_array = new ArrayList<>();
        try {
            product_array = messageConfiguration.getProductId(true);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        ArrayList<fb_elements_products> products_arr = new ArrayList<>();

        fb_elements_products fbElementsProducts1 = null;
        for (String s : product_array) {
            fbElementsProducts1 = fb_elements_products.builder()
                    .id(s)
                    .build();

            products_arr.add(fbElementsProducts1);
        }

        fb_payload fbPayload1 = com.example.flowershopbot.properties.fb_templates.product_template.fb_payload.builder()
                .template_type("product")
                .elements(products_arr)
                .build();

        fb_attchmnt fbAttchmnt1 = fb_attchmnt.builder()
                .type("template")
                .payload(fbPayload1)
                .build();
        fb_message fbMessage1 = com.example.flowershopbot.properties.fb_templates.product_template.fb_message.builder()
                .attachment(fbAttchmnt1)
                .build();
        fb_recipient fbRecipient1 = fb_recipient.builder()
                .id(personId)
                .build();
        com.example.flowershopbot.properties.fb_templates.product_template.fb_template_top fbTemplateTop = com.example.flowershopbot.properties.fb_templates.product_template.fb_template_top.builder()
                .recipient(fbRecipient1)
                .message(fbMessage1)
                .build();

        String finalJson;
        try {
            finalJson = jsonStringify(fbTemplateTop);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }

        System.out.println(finalJson);

        return finalJson;
    }

    public void sendReply(String id, String text) {
        FacebookMessageResponse response = new FacebookMessageResponse();
        response.setMessage_type("text");
        response.getRecipient().put("id", id);
        response.getMessage().put("text", text);


        HttpEntity<FacebookMessageResponse> entity = new HttpEntity<>(response);
        String result = template.postForEntity(FB_MSG_URL, entity, String.class).getBody();

        logger.info("Message result: {}", result);

    }



}

/*
    public void handleCustomerMessage(String person_id, String req_body, String userMsg){

        //Json Response Field "payload"= A ->
        if (userMsg.equals("A")) {

            webHook.sendReply(person_id,"Please send the name of the flower arrangement you would like to order");

            // Send the product Carousel
            try {
                customerPreBuiltService(req_body);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }



        } else {
            webHook.sendReply(person_id, "Hello would you like to buy a flower arrangement?");
            // Put the JSON Object into the configuration function and send the first message!(Generic Template)

            if(userMsg.equalsIgnoreCase("yes")) {
                try {
                    messageConfiguration.sendProductCarousel(req_body);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }
    }
    */

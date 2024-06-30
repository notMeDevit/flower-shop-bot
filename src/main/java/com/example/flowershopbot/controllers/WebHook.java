package com.example.flowershopbot.controllers;

import com.example.flowershopbot.configurations.MessageConfiguration;
import com.example.flowershopbot.properties.data_reqbody.Data;
import com.example.flowershopbot.properties.fb_templates.product_template.*;
import com.example.flowershopbot.properties.fbattchment_config.*;
import com.example.flowershopbot.properties.generic_template.*;
import com.example.flowershopbot.properties.webhook_config.FacebookHookRequest;
import com.example.flowershopbot.properties.webhook_config.FacebookMessageResponse;
import com.example.flowershopbot.services.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;


@Builder
@RequestMapping(value = "/v1")
@RestController
public class WebHook {


    FbAttachment fbAttachment;
    FbMessage fbMessage;
    FbAttchmnt fbAttchmnt;
    FbPayload fbPayload;
    FbRecipient fbRecipient;

    fb_template_top fb_template_top;
    fb_recipient fbRecipientTemp;
    fb_elements_products fbElementsProducts;
    fb_message fb_message;
    fb_payload fb_payload;
    fb_attchmnt fbAttchmntTemp;

    Top_GenericTemp topGenericTemp;
    GenericTemp_Message genericTempMessage;
    GenericTemp_Recipient genericTempRecipient;
    GenericTemp_Attachment genericTempAttachment;
    GenericTemp_Payload genericTempPayload;
    GenericTemp_elements genericTempElements;
    GenericTemp_Buttons genericTempButtons;

    MessageService messageService;


    @Autowired
    public WebHook(FbAttachment fbAttachment,
                   FbMessage fbMessage, FbAttchmnt fbAttchmnt,
                   FbPayload fbPayload, FbRecipient fbRecipient,
                   fb_template_top fb_template_top,
                   fb_recipient fbRecipientTemp,
                   fb_elements_products fbElementsProducts,
                   fb_message fb_message, fb_payload fb_payload, fb_attchmnt fbAttchmntTemp, Top_GenericTemp topGenericTemp,
                   GenericTemp_Message genericTempMessage,
                   GenericTemp_Recipient genericTempRecipient,
                   GenericTemp_Attachment genericTempAttachment,
                   GenericTemp_Payload genericTempPayload,
                   GenericTemp_elements genericTempElements,
                   GenericTemp_Buttons genericTempButtons,MessageService messageService) {
        this.fbAttachment = fbAttachment;
        this.fbMessage = fbMessage;
        this.fbAttchmnt = fbAttchmnt;
        this.fbPayload = fbPayload;
        this.fbRecipient = fbRecipient;
        this.fb_template_top = fb_template_top;
        this.fbRecipientTemp = fbRecipientTemp;
        this.fbElementsProducts = fbElementsProducts;
        this.fb_message = fb_message;
        this.fb_payload = fb_payload;
        this.fbAttchmntTemp = fbAttchmntTemp;
        this.topGenericTemp = topGenericTemp;
        this.genericTempMessage = genericTempMessage;
        this.genericTempRecipient = genericTempRecipient;
        this.genericTempAttachment = genericTempAttachment;
        this.genericTempPayload = genericTempPayload;
        this.genericTempElements = genericTempElements;
        this.genericTempButtons = genericTempButtons;
        this.messageService = messageService;



    }


    private final String PAGE_TOKEN = "EAAC2kuYox3wBO9sdaE7gtJJu0MKKqKwW87uScCCJNLmXXoZAsqurLSMe7FLeAuJVrYKUrFJEMShxjKbiNWnf88wQNhBZBK6ASXOSQ7xp7FzmhRpgIgi9IctgAfYR2vmteCqCXQSYCjtNyUVNKIIZBn3tZASdEkkP29EnExbL8RQbmGbYmQsXmD4dMu8u9Gps";
    private final String VERIFY_TOKEN = "someRandomnString256";
    //this is for reply messages
    private final String FB_MSG_URL = "https://graph.facebook.com/v2.6/me/messages?access_token="
            + PAGE_TOKEN;

    //logger to watch what is happening in our bot
    private final Logger logger = LoggerFactory.getLogger(WebHook.class);
    private final RestTemplate template = new RestTemplate();
    private final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    private final MessageConfiguration messageConfiguration = new MessageConfiguration();


    //This is necessary for register a webhook in facebook
    @GetMapping(value = "/webhook")
    @ResponseStatus(HttpStatus.OK)
    public String get(@RequestParam(name = "hub.verify_token") String token,
                      @RequestParam(name = "hub.challenge") String challenge) {
        if (token != null && !token.isEmpty() && token.equals(VERIFY_TOKEN)) {
            return challenge;
        } else {
            return "Wrong Token";
        }
    }

    @PostMapping(value = "/webhook/test_function")
    public void testFunction() throws IOException {
        boolean idFlag = false;
        String psid = "6567177600041771";
        ArrayList<String> productNames = messageConfiguration.getProductId(idFlag);

        String btnTemplate = messageService.buttonTemplateBuilder(psid,productNames);
        messageConfiguration.sendButtonTemplate(btnTemplate);
    }



    //This method  reply all messages with: 'This is a test message'
    @PostMapping(value = "/webhook")
    @ResponseStatus(HttpStatus.OK)
    public void post(@RequestBody FacebookHookRequest request) throws JsonProcessingException { //@RequestBody ){


        ObjectMapper mapper = new ObjectMapper();
        String jsonBook = mapper.writeValueAsString(request);
        logger.info("{}", jsonBook);

       


        /*
            request.getEntry().forEach(e -> {
                e.getMessaging().forEach(m -> {

                    String id = m.getSender().get("id");
                    String payload = null;
                    String userMsg = null;
                    String github = null;

                    if(m.getMessage() == null){
                        payload = m.getPostback().getPayload();
                        System.out.println(payload);
                    }
                    else{
                        userMsg = m.getMessage().getText();
                    }

                    try {
                            messageService.botRouteMessage(id, userMsg, payload);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                });
            });

            */

    }


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




  /*  if(m.getMessage().getText() == null){
        m.getMessage().getAttachments().forEach(z -> {



         if(z.getType().equals("image") || z.getType().equals("fallback") || z.getType().equals("template")){

                                    String personId = m.getSender().get("id");
                                    String attachmentId = z.getPayload().getUrl();
                                    //logger.info("{}",attachmentId);

                                    sendReply(personId,"Sending attachment back now.");
                                    attachmentBuilder(attachmentId,"image",personId);

                            }
                        });}

                    else {

                        String id = m.getSender().get("id");
                        String userMsg = m.getMessage().getText();
                        boolean isMatch = false;
                        ArrayList<String> product_array = new ArrayList<>();



                        if (isNumeric(userMsg)) {

                            sendReply(id, "Checking Database for flower arrangement");
                           /* try {
                                isMatch = messageConfiguration.checkDb(Integer.valueOf(userMsg));
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }

        isMatch = messageService.userMessageDatabase(isMatch,userMsg);



        if (isMatch) {
            sendReply(id, "Flower Arrangement Matches");

        } else {
            sendReply(id, "Flower Arrangement does not match. Please resend flower arrangement id.");
        }
        // sendReply("The price is <var> please confirm and then answer picture postback, and then proceed with payment
        //
        */



/*
    //Custom controller for Noahs Company Endpoint
    @PostMapping(value = "/companyEndpoint")
    public void postUponClick(@RequestBody Data data){ // Model Class Data is Annotated with "@RequestBody"


        // Model class contains Getters and Setters by default ->
        // Use getters and setter in C# (I know that C# has those when you create model classes I think)
        int originalRequestNumber = data.getOriginalRequestNumber();
        // Store json in variable ^^
        /*
            data{
                  "OriginalRequestNumber": int

                }

    }
 */




package com.example.flowershopbot.configurations;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.OkHttpClient.Builder;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class MessageConfiguration {


    public void sendMessage() throws IOException {

        System.out.println("");


        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n    \"recipient\": {\n        \"id\": 6567177600041771\n    },\n    \"message\": {\n        \"text\": \"Thanks!\"\n    }\n}");
        Request request = new Request.Builder()
                .url("https://graph.facebook.com/v2.6/me/messages?access_token=EAAC2kuYox3wBO9sdaE7gtJJu0MKKqKwW87uScCCJNLmXXoZAsqurLSMe7FLeAuJVrYKUrFJEMShxjKbiNWnf88wQNhBZBK6ASXOSQ7xp7FzmhRpgIgi9IctgAfYR2vmteCqCXQSYCjtNyUVNKIIZBn3tZASdEkkP29EnExbL8RQbmGbYmQsXmD4dMu8u9Gps")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();




    }
}

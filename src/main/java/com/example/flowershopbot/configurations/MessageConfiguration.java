package com.example.flowershopbot.configurations;
import com.example.flowershopbot.properties.fb_templates.catalog_data.ReadCatalogTable;
import com.example.flowershopbot.properties.fb_templates.catalog_data.ReadCatalog_Data;
import com.example.flowershopbot.properties.supabase_config.ReadTable;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.ArrayList;

@Configuration
public class MessageConfiguration {


        public boolean checkDb(int flowerId) throws IOException {

                boolean idMatchFlag = false;

                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("text/plain");
                //RequestBody body = RequestBody.create(mediaType, "");
                Request request = new Request.Builder()
                        .url("https://ibaickfbjuhjpopuxspe.supabase.co/rest/v1//flower_arrangements?select=*")
                        .method("GET", null)//body
                        .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImliYWlja2ZianVoanBvcHV4c3BlIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTY5MTQ0NzY1MywiZXhwIjoyMDA3MDIzNjUzfQ.iTWfo0ujgowylPA3_y7UOO-MjIpASim94fRtA4dK1F0")
                        .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImliYWlja2ZianVoanBvcHV4c3BlIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTY5MTQ0NzY1MywiZXhwIjoyMDA3MDIzNjUzfQ.iTWfo0ujgowylPA3_y7UOO-MjIpASim94fRtA4dK1F0")
                        .build();
                Response response = client.newCall(request).execute();

                String jsonData = response.body().string();

                ObjectMapper mapper = new ObjectMapper();
                ReadTable[] jResponse = mapper.readValue(jsonData,ReadTable[].class);

                for(ReadTable items : jResponse){
                        if(items.flower_id == flowerId){
                                idMatchFlag = true;
                        }
                        else{
                                idMatchFlag = false;
                        }
                }

                return idMatchFlag;

        }

        public void sendAttachment(String req_body) throws IOException {

                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(req_body, mediaType);
                Request request = new Request.Builder()
                        .url("https://graph.facebook.com/v2.6/me/messages?access_token=EAAC2kuYox3wBO9sdaE7gtJJu0MKKqKwW87uScCCJNLmXXoZAsqurLSMe7FLeAuJVrYKUrFJEMShxjKbiNWnf88wQNhBZBK6ASXOSQ7xp7FzmhRpgIgi9IctgAfYR2vmteCqCXQSYCjtNyUVNKIIZBn3tZASdEkkP29EnExbL8RQbmGbYmQsXmD4dMu8u9Gps")
                        .method("POST", body)
                        .addHeader("Content-Type", "application/json")
                        .build();
                Response response = client.newCall(request).execute();





        }

        public void sendButtonTemplate(String req_body) throws IOException {

                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, req_body);
                Request request = new Request.Builder()
                        .url("https://graph.facebook.com/v8.0/me/messages?access_token=EAAC2kuYox3wBO9sdaE7gtJJu0MKKqKwW87uScCCJNLmXXoZAsqurLSMe7FLeAuJVrYKUrFJEMShxjKbiNWnf88wQNhBZBK6ASXOSQ7xp7FzmhRpgIgi9IctgAfYR2vmteCqCXQSYCjtNyUVNKIIZBn3tZASdEkkP29EnExbL8RQbmGbYmQsXmD4dMu8u9Gps")
                        .method("POST", body)
                        .addHeader("Content-Type", "application/json")
                        .build();
                Response response = client.newCall(request).execute();

        }

        public ArrayList<String> getProductId(boolean idFlag) throws IOException {

                String access_token = "EAAC2kuYox3wBO9sdaE7gtJJu0MKKqKwW87uScCCJNLmXXoZAsqurLSMe7FLeAuJVrYKUrFJEMShxjKbiNWnf88wQNhBZBK6ASXOSQ7xp7FzmhRpgIgi9IctgAfYR2vmteCqCXQSYCjtNyUVNKIIZBn3tZASdEkkP29EnExbL8RQbmGbYmQsXmD4dMu8u9Gps";

                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("text/plain");
                //RequestBody body = RequestBody.create(mediaType, "");
                Request request = new Request.Builder()
                        .url("https://graph.facebook.com/v17.0/959906101776280/products?access_token=" + access_token)
                        .method("GET", null)
                        .build();
                Response response = client.newCall(request).execute();

                ObjectMapper mapper = new ObjectMapper();
                ReadCatalogTable jResponseId = mapper.readValue(response.toString(),ReadCatalogTable.class);
                //ArrayList<String> arr = new ArrayList<>();
                ArrayList<String> getData = new ArrayList<>();

                // Get product_id from Json
                if(idFlag) {
                        for (ReadCatalog_Data items : jResponseId.getData()) {
                                getData.add(items.getId());
                        }
                        System.out.println(getData);
                }
                // Get Product Name from Json
                else{
                        for (ReadCatalog_Data items : jResponseId.getData()) {
                                getData.add(items.getName());
                        }
                        System.out.println(getData);

                }

                return getData;

               /* request.getEntry().forEach(e -> {
                                e.getMessaging().forEach(m -> {
                                */


        }

        public void sendProductCarousel(String req_body) throws IOException {


                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, req_body);
                Request request = new Request.Builder()
                        .url("https://graph.facebook.com/v8.0/me/messages?access_token=EAAC2kuYox3wBO9sdaE7gtJJu0MKKqKwW87uScCCJNLmXXoZAsqurLSMe7FLeAuJVrYKUrFJEMShxjKbiNWnf88wQNhBZBK6ASXOSQ7xp7FzmhRpgIgi9IctgAfYR2vmteCqCXQSYCjtNyUVNKIIZBn3tZASdEkkP29EnExbL8RQbmGbYmQsXmD4dMu8u9Gps")
                        .method("POST", body)
                        .addHeader("Content-Type", "application/json")
                        .build();
                Response response = client.newCall(request).execute();

        }

        public void updateDb(int personId) throws IOException {


                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, "{\n   \n    \"customer_personId\":123456\n\n}");
                Request request = new Request.Builder()
                        // -> Is there supposed to be a second forward slash after v1//? lol
                        .url("https://ibaickfbjuhjpopuxspe.supabase.co/rest/v1//customers")
                        .method("POST", body)
                        .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImliYWlja2ZianVoanBvcHV4c3BlIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTY5MTQ0NzY1MywiZXhwIjoyMDA3MDIzNjUzfQ.iTWfo0ujgowylPA3_y7UOO-MjIpASim94fRtA4dK1F0")
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Prefer", "return=minimal")
                        .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImliYWlja2ZianVoanBvcHV4c3BlIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTY5MTQ0NzY1MywiZXhwIjoyMDA3MDIzNjUzfQ.iTWfo0ujgowylPA3_y7UOO-MjIpASim94fRtA4dK1F0")
                        .build();
                Response response = client.newCall(request).execute();


        }


        public void sendGenericTemplate(String req_body) throws IOException {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType,req_body);
                Request request = new Request.Builder()
                        .url("https://graph.facebook.com/v8.0/me/messages?access_token=EAAC2kuYox3wBO9sdaE7gtJJu0MKKqKwW87uScCCJNLmXXoZAsqurLSMe7FLeAuJVrYKUrFJEMShxjKbiNWnf88wQNhBZBK6ASXOSQ7xp7FzmhRpgIgi9IctgAfYR2vmteCqCXQSYCjtNyUVNKIIZBn3tZASdEkkP29EnExbL8RQbmGbYmQsXmD4dMu8u9Gps")
                        .method("POST", body)
                        .addHeader("Content-Type", "application/json")
                        .build();
                Response response = client.newCall(request).execute();


        }




}


//for(int i = 0; i < ; i++)

//ArrayList<ReadTable> jArr = new ArrayList<>();
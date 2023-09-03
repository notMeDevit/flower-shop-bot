package com.example.flowershopbot.configurations;
import com.example.flowershopbot.properties.supabase_config.ReadTable;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

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
                        if(items.getFlower_id() == flowerId){
                                idMatchFlag = true;
                        }
                        else{
                                idMatchFlag = false;
                        }
                }

                return idMatchFlag;

        }

        public void sendAttachment(String url){





        }



        public void updateDb(int personId) throws IOException {


                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, "{\n   \n    \"customer_personId\":123456\n\n}");
                Request request = new Request.Builder()
                        .url("https://ibaickfbjuhjpopuxspe.supabase.co/rest/v1//customers")
                        .method("POST", body)
                        .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImliYWlja2ZianVoanBvcHV4c3BlIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTY5MTQ0NzY1MywiZXhwIjoyMDA3MDIzNjUzfQ.iTWfo0ujgowylPA3_y7UOO-MjIpASim94fRtA4dK1F0")
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Prefer", "return=minimal")
                        .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImliYWlja2ZianVoanBvcHV4c3BlIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTY5MTQ0NzY1MywiZXhwIjoyMDA3MDIzNjUzfQ.iTWfo0ujgowylPA3_y7UOO-MjIpASim94fRtA4dK1F0")
                        .build();
                Response response = client.newCall(request).execute();


        }





}


//for(int i = 0; i < ; i++)

//ArrayList<ReadTable> jArr = new ArrayList<>();
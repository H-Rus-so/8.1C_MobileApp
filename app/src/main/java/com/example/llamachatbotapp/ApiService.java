package com.example.llamachatbotapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import java.util.Map;

public interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("/chat")
    Call<BotResponse> sendMessage(@Body Map<String, Object> body);
}

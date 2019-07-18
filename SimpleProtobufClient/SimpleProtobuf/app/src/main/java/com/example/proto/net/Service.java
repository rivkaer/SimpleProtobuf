package com.example.proto.net;

import com.example.proto.Message;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

public interface Service {

    @GET("/test")
    Observable<Message.LoginResponse> sendMessage();

    @GET("/test-json")
    Observable<JsonBeanMessage> sendJsonMessage();
}
package com.example.proto.controller;

import com.example.proto.Message;
import com.example.proto.bean.JsonBeanMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @GetMapping(value = "/test",produces = "application/x-protobuf; charset=utf-8")
    public Message.LoginResponse testSimpleProtobuf(){
        Message.LoginResponse.Builder builder = Message.LoginResponse.newBuilder();
        builder.setCode(200);
        builder.setMsg("登陆成功");
        return builder.build();
    }

    @GetMapping(value = "/test-json",produces = "application/json; charset=utf-8")
    public JsonBeanMessage testSimpleJson(){
        JsonBeanMessage builder = new JsonBeanMessage();
        builder.setCode(100);
        builder.setMsg("登陆成功 - Json");
        return builder;
    }
}

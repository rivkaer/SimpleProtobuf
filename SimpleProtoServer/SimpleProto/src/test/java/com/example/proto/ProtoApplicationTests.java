package com.example.proto;

import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProtoApplicationTests {

    @Test
    public void contextLoads() throws InvalidProtocolBufferException {
        Message.LoginRequest.Builder builder = Message.LoginRequest.newBuilder();
        builder.setUsername("rivkaer");
        builder.setPwd("123456");

        Message.LoginRequest person = builder.build();
        System.out.println("before:" + person);

        System.out.println("===Person Byte:");
        for (byte b : person.toByteArray()) {
            System.out.print(b);
        }
        System.out.println("================");

        byte[] byteArray = person.toByteArray();
        Message.LoginRequest p2 =  Message.LoginRequest.parseFrom(byteArray);
        System.out.println("after name:" + p2.getUsername());
        System.out.println("after pwd:" + p2.getPwd());
    }

    @Test
    public void testSimpleRemoteLoginTestPort(){
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            URI uri = new URI("http",null,"127.0.0.1",8060,"/test","",null);
            HttpGet httpGet = new HttpGet(uri);
            HttpResponse execute = httpClient.execute(httpGet);
            Message.LoginResponse loginResponse = Message.LoginResponse.parseFrom(execute.getEntity().getContent());
            System.out.println("response -->" +loginResponse.getMsg());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

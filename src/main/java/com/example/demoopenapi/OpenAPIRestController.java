package com.example.demoopenapi;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
public class OpenAPIRestController {
    @GetMapping("/translate")
    public ResponseEntity<String> data(@RequestParam(name = "query",defaultValue = "Hello") String query){
        RestTemplate restTemplate=new RestTemplate();
        Map<String,String> message=new HashMap<>();
        message.put("role","user");
        message.put("content",query);
        Map<String,Object> request=new HashMap<>();
        request.put("messages", List.of(message));
        request.put("model","gpt-3.5-turbo-0301");
        HttpHeaders headers=new HttpHeaders();
        headers.set("Authorization","Bearer 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");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map> httpEntity=new HttpEntity<>(request,headers);
        ResponseEntity<String> exchange = restTemplate.exchange(
                "https://api.openai.com/v1/chat/completions",
                HttpMethod.POST,
                httpEntity, String.class
        );
        return exchange;
    }
}

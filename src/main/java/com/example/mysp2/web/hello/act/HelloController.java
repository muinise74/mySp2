package com.example.mysp2.web.hello.act;

import com.example.mysp2.web.hello.vo.HelloResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/vo")
    public HelloResponse helloVO(HelloResponse hRes) {
        return hRes;
    }

}

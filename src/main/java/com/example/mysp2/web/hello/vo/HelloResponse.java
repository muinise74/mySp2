package com.example.mysp2.web.hello.vo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class HelloResponse {

    private final String name;
    private final int amount;

}

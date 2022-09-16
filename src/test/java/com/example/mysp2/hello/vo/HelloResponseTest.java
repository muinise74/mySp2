package com.example.mysp2.hello.vo;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HelloResponseTest {

    @Test
    public void lombokTest() {

        String name = "test";
        int amount = 1000;

        HelloResponse hRes = new HelloResponse(name,amount);

        assertThat(hRes.getName()).isEqualTo(name);
        assertThat(hRes.getAmount()).isEqualTo(amount);

    }
}

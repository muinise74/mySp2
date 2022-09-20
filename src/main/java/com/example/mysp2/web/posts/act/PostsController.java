package com.example.mysp2.web.posts.act;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class PostsController {

    @RequestMapping("/posts/save")
    public String postsSave() {
        return "posts/save";
    }

}

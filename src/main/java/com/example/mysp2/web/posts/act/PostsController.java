package com.example.mysp2.web.posts.act;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PostsController {

    @RequestMapping("/posts/save")
    public String postsSave() {
        return "posts/save";
    }

}

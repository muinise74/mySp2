package com.example.mysp2.web.home.act;

import com.example.mysp2.web.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    final private PostsService ps;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("posts",ps.findAllDesc());
        return "index";
    }

}

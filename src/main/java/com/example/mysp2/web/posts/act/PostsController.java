package com.example.mysp2.web.posts.act;

import com.example.mysp2.web.posts.service.PostsService;
import com.example.mysp2.web.posts.vo.PostsResponseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostsController {

    final private PostsService ps;

    @RequestMapping("/posts/save")
    public String postsSave() {
        return "posts/save";
    }

    @RequestMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseVO prv = ps.findById(id);
        model.addAttribute("post",prv);
        return "posts/update";
    }

}

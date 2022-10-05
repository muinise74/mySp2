package com.example.mysp2.web.home.act;

import com.example.mysp2.config.auth.dto.SessionUser;
import com.example.mysp2.web.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class HomeController {

    final private PostsService ps;
    private final HttpSession httpSession;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("posts",ps.findAllDesc());

        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("LoginUserName",user.getName());
            System.out.println(user.getName());
        }
        return "index";
    }

}

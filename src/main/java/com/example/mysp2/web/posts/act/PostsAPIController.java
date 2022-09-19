package com.example.mysp2.web.posts.act;

import com.example.mysp2.web.posts.vo.PostsResponseVO;
import com.example.mysp2.web.posts.vo.PostsSaveReqVO;
import com.example.mysp2.web.posts.vo.PostsUpdateReqVO;
import com.example.mysp2.web.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsAPIController {

    final private PostsService ps;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveReqVO psrv) {
        return ps.save(psrv);
    }

    @PostMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateReqVO purv) {
        return ps.update(id,purv);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseVO findById (@PathVariable Long id) {
        return ps.findById(id);
    }

}

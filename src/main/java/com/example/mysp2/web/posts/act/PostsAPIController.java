package com.example.mysp2.web.posts.act;

import com.example.mysp2.web.posts.vo.PostsSaveReqVO;
import com.example.mysp2.web.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostsAPIController {

    final private PostsService ps;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveReqVO psrv) {
        return ps.save(psrv);
    }

}

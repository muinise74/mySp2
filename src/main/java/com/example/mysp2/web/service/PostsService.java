package com.example.mysp2.web.service;

import com.example.mysp2.domain.posts.Posts;
import com.example.mysp2.domain.posts.PostsRepository;
import com.example.mysp2.web.posts.vo.PostsResponseVO;
import com.example.mysp2.web.posts.vo.PostsSaveReqVO;
import com.example.mysp2.web.posts.vo.PostsUpdateReqVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository pr;

    @Transactional
    public Long save(PostsSaveReqVO psrv) {
        return pr.save(psrv.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateReqVO purv) {

        Posts posts = pr.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        posts.update(purv.getTitle(),purv.getContent());
        return id;
    }

    public PostsResponseVO findById(Long id) {
        Posts entity = pr.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        return new PostsResponseVO(entity);
    }
}

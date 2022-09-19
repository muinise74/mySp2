package com.example.mysp2.web.service;

import com.example.mysp2.domain.posts.PostsRepository;
import com.example.mysp2.web.posts.vo.PostsSaveReqVO;
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

}

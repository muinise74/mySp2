package com.example.mysp2.web.posts.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateReqVO {

    private String title;
    private String content;

    @Builder
    public PostsUpdateReqVO(String title, String content) {
        this.title = title;
        this.content = content;
    }

}

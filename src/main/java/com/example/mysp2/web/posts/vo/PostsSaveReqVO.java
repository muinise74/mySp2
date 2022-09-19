package com.example.mysp2.web.posts.vo;

import com.example.mysp2.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveReqVO {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveReqVO(String title,String content,String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder().title(title).content(content).author(author).build();
    }

}

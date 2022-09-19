package com.example.mysp2.web.posts.vo;

import com.example.mysp2.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseVO {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseVO(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }

}

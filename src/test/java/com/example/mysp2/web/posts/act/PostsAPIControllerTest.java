package com.example.mysp2.web.posts.act;

import com.example.mysp2.domain.posts.Posts;
import com.example.mysp2.domain.posts.PostsRepository;
import com.example.mysp2.web.posts.vo.PostsSaveReqVO;
import com.example.mysp2.web.posts.vo.PostsUpdateReqVO;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsAPIControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository pr;

    @After
    public void tearDown() throws Exception {
        pr.deleteAll();
    }

    @Test
    public void postsRegisterTest() throws Exception{

        String title = "title";
        String content = "content";
        String author = "author";

        PostsSaveReqVO psrv = PostsSaveReqVO.builder().title(title).content(content).author(author).build();

        String url = "http://localhost:"+port+"/api/v1/posts";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, psrv, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = pr.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);

    }

    @Test
    public void PostsUpdateTest() throws Exception {

        Posts savedPosts = pr.save(Posts.builder().title("test").content("test").author("test").build());
        Long updateId = savedPosts.getId();
        String expected = "test02";

        PostsUpdateReqVO purv = PostsUpdateReqVO.builder().title(expected).content(expected).build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        HttpEntity<PostsUpdateReqVO> requestEntity = new HttpEntity<>(purv);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = pr.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expected);
        assertThat(all.get(0).getContent()).isEqualTo(expected);

    }

}

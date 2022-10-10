package com.example.mysp2.web.posts.act;

import com.example.mysp2.domain.posts.Posts;
import com.example.mysp2.domain.posts.PostsRepository;
import com.example.mysp2.web.posts.vo.PostsSaveReqVO;
import com.example.mysp2.web.posts.vo.PostsUpdateReqVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(roles="USER")
    public void postsRegisterTest() throws Exception{

        //given
        String title = "title";
        String content = "content";
        String author = "author";
        PostsSaveReqVO psrv = PostsSaveReqVO.builder().title(title).content(content).author(author).build();

        String url = "http://localhost:"+port+"/api/v1/posts";

        //when

        //MediaType.APPLICATION_JSON_UTF8 >> deprecated >> modify MediaType.APPLICATION_JSON
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(psrv)))
                .andExpect(status().isOk());

        //then
        List<Posts> all = pr.findAll();
        System.out.println(">>>>> createdDate : " + all.get(0).getCreatedDate() + ", modifieddate : " + all.get(0).getModifiedDate());
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);

    }

    @Test
    @WithMockUser(roles="USER")
    public void PostsUpdateTest() throws Exception {

        //given
        Posts savedPosts = pr.save(Posts.builder().title("test").content("test").author("test").build());
        Long updateId = savedPosts.getId();
        String expected = "test02";

        PostsUpdateReqVO purv = PostsUpdateReqVO.builder().title(expected).content(expected).build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(purv)))
                .andExpect(status().isOk());

        //then
        List<Posts> all = pr.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expected);
        assertThat(all.get(0).getContent()).isEqualTo(expected);

    }

}

package com.k.myreactorapi;

import com.k.myreactorapi.dao.PostDataDao;
import com.k.myreactorapi.model.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MyReactorApiApplication.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
public class MyReactorApiApplicationTests {
  @LocalServerPort
  int randomServerPort;

  @Autowired
  private PostDataDao postDataDao;

  @Test
  public void contextLoads() {
    WebClient client = WebClient.builder()
        .baseUrl("http://localhost:" + randomServerPort)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();

    Post postedPost = new Post(null, "post author", "post article");
    Flux<Post> createPost = client.post()
        .uri("/posts")
        .body(BodyInserters.fromPublisher(Mono.just(postedPost), Post.class))
        .retrieve()
        .bodyToFlux(Post.class);

    Mono<Post> postMono = client.get()
        .uri("/posts/{id}", "1")
        .retrieve()
        .bodyToMono(Post.class)
        .doOnError(e -> System.out.println(e.getMessage()));

    Flux<Post> postFlux = client.get()
        .uri("/posts")
        .retrieve()
        .bodyToFlux(Post.class);

    postDataDao.deleteAll()
        .thenMany(createPost)
        .thenMany(createPost)
        .thenMany(postFlux)
        .subscribe(this::print);

    await().atMost(1, TimeUnit.MINUTES)
        .until(() -> postFlux.blockLast() != null);

    Post post = postFlux.blockFirst();
    assertNotNull(post.getId());
    assertEquals("post author", post.getAuthor());
    assertEquals("post article", post.getArticle());

    System.out.println("finished");
  }

  private void print(Post post) {
    System.out.println(post.toString());
  }
}

package com.k.myreactorapi;

import com.k.myreactorapi.model.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MyReactorApiApplication.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
public class MyReactorApiApplicationTests {
  @LocalServerPort
  int randomServerPort;

  @Test
  public void contextLoads() throws InterruptedException {
    System.out.println(randomServerPort);
    WebClient client = WebClient.builder()
        .baseUrl("http://localhost:" + randomServerPort)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();

    System.out.println("Posting post");
    Post postedPost = new Post(null, "post author", "post article");
    client.post()
        .uri("/posts")
        .body(BodyInserters.fromPublisher(Mono.just(postedPost), Post.class))
        .retrieve()
        .bodyToMono(Void.class)
        .block();

    System.out.println("get mono");
    Mono<Post> postMono = client.get()
        .uri("/posts/{id}", "1")
        .retrieve()
        .bodyToMono(Post.class);
    postMono.subscribe(this::print);

    Post monoPost = postMono.block();

    System.out.println("get flux");

    Flux<Post> postFlux = client.get()
        .uri("/posts")
        .retrieve()
        .bodyToFlux(Post.class);
    postFlux.subscribe(this::print);

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

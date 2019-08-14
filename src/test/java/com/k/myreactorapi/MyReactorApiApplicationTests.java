package com.k.myreactorapi;

import com.k.myreactorapi.model.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MyReactorApiApplication.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
public class MyReactorApiApplicationTests {
  @LocalServerPort
  int randomServerPort;

  @Test
  public void contextLoads() throws InterruptedException {
    System.out.println(randomServerPort);
    WebClient client = WebClient.create("http://localhost:" + randomServerPort);

    System.out.println("get mono");
    Mono<Post> postMono = client.get()
        .uri("/posts/{id}", "1")
        .retrieve()
        .bodyToMono(Post.class);
    Thread.sleep(10000);

    System.out.println("get flux");
    postMono.subscribe(System.out::println).dispose();

    Flux<Post> postFlux = client.get()
        .uri("/posts")
        .retrieve()
        .bodyToFlux(Post.class)
        .checkpoint("flux checkpoint", true);

    postFlux.subscribe(System.out::println);
    Thread.sleep(10000);
    System.out.println("finished");
  }

}

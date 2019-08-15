package com.k.myreactorapi.service;

import com.k.myreactorapi.model.Post;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;

@Service
public class PostService {
  private final List<Post> postList;

  public PostService() {
    Post post1 = new Post();
    post1.setAuthor("test author 1");
    post1.setArticle("test article 1");
    post1.setCreatedAt(Instant.now());

    Post post2 = new Post();
    post2.setAuthor("test author 2");
    post2.setArticle("test article 2");
    post2.setCreatedAt(Instant.now());

    Post post3 = new Post();
    post3.setAuthor("test author 3");
    post3.setArticle("test article 3");
    post3.setCreatedAt(Instant.now());
    this.postList = List.of(post1, post2, post3);
  }

  public Mono getPostById(String id) {
    Post post = new Post();
    post.setAuthor("test author");
    post.setArticle("test article");
    post.setCreatedAt(Instant.now());
    return Mono.just(post);
  }

  public Flux<Post> getPosts() {
    return Flux.fromIterable(postList);
  }
}
